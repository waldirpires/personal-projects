package com.ericsson.msoimport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ericsson.msoimport.FileReader.LineProcessor;
import com.ericsson.msoimport.domain.Mso;
import com.ericsson.msoimport.domain.MsoOverride;

public class ReadInputScript {

	private final String fileName;

	private static final Logger logger = Logger.getLogger(ReadInputScript.class);

	static {
		LogAppender.addAppender(logger);
	}

	public ReadInputScript(String fileName) {
		this.fileName = fileName;
	}

	private MsoOverride createMsoOVerride(String fieldXPath, String searchValue, String replacementValue) {
		MsoOverride msoOverride = new MsoOverride();
		msoOverride.setFieldXPath(fieldXPath);
		msoOverride.setSearchValue(searchValue);
		msoOverride.setReplacementValue(replacementValue);
		return msoOverride;
	}

	private void readMsoMappings(final Mso mso) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		// Insert into ADS.MSO_MAPPINGS (VARIETY,MSO_ID,PRE,POST) values
		// ('CATEGORY',1201,'ESPN/Dallas Cheerleaders','Free On
		// Demand/ESPN/Dallas Cheerleaders');
		final Pattern pattern = Pattern.compile(String.format(
		        "Insert into ADS\\.MSO_MAPPINGS \\(VARIETY,MSO_ID,PRE,POST\\) values \\('(.*)',%d,'(.*)','(.*)'.*", mso.Id));
		fileReader.readLine(new LineProcessor() {

			@Override
			public boolean OnLineRead(int lineNumber, String lineContents) {
				Matcher matcher = pattern.matcher(lineContents);
				if (matcher.find()) {
					String variety = matcher.group(1);
					String searchValue = matcher.group(2);
					String replacementValue = matcher.group(3);
					if ("CATEGORY".equals(variety)) {
						mso.msoOverrides.add(createMsoOVerride("/ECMFSpec/Package/Title/Fields/Categories/Category/Text", searchValue, replacementValue));
					} else if ("PRODUCT".equals(variety)) {
						// PRODUCT overrides must be consistent for all assetss
						mso.msoOverrides.add(createMsoOVerride("/ECMFSpec/Package/Fields/ProductInfo/Name", searchValue, replacementValue));
						mso.msoOverrides.add(createMsoOVerride("/ECMFSpec/Package/Title/Fields/ProductInfo/Name", searchValue, replacementValue));
						mso.msoOverrides.add(createMsoOVerride("/ECMFSpec/Package/Movie/Fields/ProductInfo/Name", searchValue, replacementValue));
						mso.msoOverrides.add(createMsoOVerride("/ECMFSpec/Package/Preview/Fields/ProductInfo/Name", searchValue, replacementValue));
						mso.msoOverrides.add(createMsoOVerride("/ECMFSpec/Package/Poster/Fields/ProductInfo/Name", searchValue, replacementValue));
					} else
						throw new RuntimeException("Invalid Variety on line " + lineNumber + ": " + lineContents);
				}
				return true;
			}
		});
	}

	public List<Mso> readMso() throws IOException {
		logger.debug("Reading " + fileName);
		final List<Mso> list = new ArrayList<Mso>();
		FileReader fileReader = new FileReader(fileName);
		// Insert into ADS.MSO (MSO_ID,NAME,CREATION_DATE,ACTIVE) values
		// (1060,'espn',to_date('13-DEC-07','DD-MON-RR'),1);
		final Pattern pattern = Pattern.compile("Insert into ADS\\.MSO \\(MSO_ID,NAME,CREATION_DATE,ACTIVE\\) values \\(([\\d]*),'([0-0a-zA-Z]*)',.*");
		fileReader.readLine(new LineProcessor() {

			@Override
			public boolean OnLineRead(int lineNumber, String lineContents) throws IOException {
				Matcher matcher = pattern.matcher(lineContents);
				if (matcher.find()) {
					int id = Integer.valueOf(matcher.group(1));
					String name = matcher.group(2);
					final Mso mso = new Mso(id, name);
					readMsoMappings(mso);
					logger.debug(mso);
					list.add(mso);
				}
				return true;
			}
		});
		return list;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String fileName = "C:/Users/ampaiva/Downloads/MSO_export_oct_24.sql";
		ReadInputScript readInputScript = new ReadInputScript(fileName);
		List<Mso> list = readInputScript.readMso();
		System.out.println(list.size());
		for (Mso mso : list) {
			if (!"espn".equals(mso.Name))
				continue;
			System.out.println(mso);
			for (MsoOverride msoOverride : mso.msoOverrides) {
				System.out.println(msoOverride);
			}
		}
	}

}
