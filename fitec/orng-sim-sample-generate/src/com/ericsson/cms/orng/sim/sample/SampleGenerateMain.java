package com.ericsson.cms.orng.sim.sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class SampleGenerateMain {

	private static final String BASE_ADI_BASE_XML = "base/ADI-base.XML";

	public static void main(String[] args) throws IOException {
		if (args.length < 3)
		{
			System.out.println("Usage: SampleGenerateMain <size> <start> <watchFolder-path>");
			System.out.println("<size>: the number of packages to produce (e.g. 001)");
			System.out.println("<start>: the starting index to use for the asset ID (e.g. 10 -> 001, 002, 003 . . . 010)");
			System.out.println("<watchFolder-path>: the full path of the watchfolder - location where packages will be published");
			System.exit(0);
		}		

		// change the xml file
		File xmlFile = new File(BASE_ADI_BASE_XML);
		assert xmlFile.exists();
		BufferedReader br = new BufferedReader(new FileReader(xmlFile));
		StringBuffer buffer = new StringBuffer();
		
		String r = br.readLine();
		while (r != null)
		{
			buffer.append(r).append("\n");
			r = br.readLine();
		}
		br.close();
		System.out.println("Read: " + buffer.length() + " characters from XML base file.");
		
		int size = 
				//10; 
				Integer.parseInt(args[0]);
		int start = 
		//20; 
				Integer.parseInt(args[1]);
		 String cmsWatchfolder = args[2];
		System.out.println("Size : " + size);
		System.out.println("Start: " + start);
		int index = start;
		for (int i = 0; i < size; i++)
		{			 
			System.out.println("Generating for " + index);
			String value = String.format("%03d", index);
			 System.out.println(String.format("%03d", index));
			 // update ADI file
			 String out = new String(buffer.toString());
			 out = out.replace("$$$", value);
			 String adiFileName = "out/ADI-"+value+".XML";			
			File outFile = new File(adiFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
			 bw.write(out);
			 bw.close();
			 System.out.println("ADI file generated.");
			 assert outFile.exists();
			 // change file names
			 FileUtils.copyFile(new File("base/movie-cerlopes-$$$.ts"), new File("out/movie-cerlopes-" + value +".ts"));
			 FileUtils.copyFile(new File("base/POSTER_CERLOPES_TEST_000$$$_COVER1.jpg"), new File("out/POSTER_CERLOPES_TEST_000"+value+"_COVER1.jpg"));
			 FileUtils.copyFile(new File("base/POSTER_CERLOPES_TEST_000$$$_COVER2.jpg"), new File("out/POSTER_CERLOPES_TEST_000"+value+"_COVER2.jpg"));
			 FileUtils.copyFile(new File("base/poster-cerlopes-$$$.jpg"), new File("out/poster-cerlopes-"+value+".jpg"));
			 FileUtils.copyFile(new File("base/preview-cerlopes-$$$.sent"), new File("out/preview-cerlopes-"+value+".sent"));
			 FileUtils.copyFile(new File("base/preview-cerlopes-$$$.ts"), new File("out/preview-cerlopes-"+value+".ts"));
			 FileUtils.copyFile(new File("base/preview-cerlopes-$$$.ts"), new File("out/preview-cerlopes-"+value+".ts.sent"));
			 System.out.println("asset files generated.");
			 // copy all files to CMS
			 copyFileToFolder(adiFileName, cmsWatchfolder);
			 copyFileToFolder("out/movie-cerlopes-" + value +".ts", cmsWatchfolder);
			 copyFileToFolder("out/POSTER_CERLOPES_TEST_000"+value+"_COVER1.jpg", cmsWatchfolder);
			 copyFileToFolder("out/POSTER_CERLOPES_TEST_000"+value+"_COVER2.jpg", cmsWatchfolder);
			 copyFileToFolder("out/poster-cerlopes-"+value+".jpg", cmsWatchfolder);
			 copyFileToFolder("out/preview-cerlopes-"+value+".sent", cmsWatchfolder);
			 copyFileToFolder("out/preview-cerlopes-"+value+".ts", cmsWatchfolder);
			 copyFileToFolder("out/preview-cerlopes-"+value+".ts.sent", cmsWatchfolder);
			 System.out.println("Files copied to CMS watch folder: " + cmsWatchfolder);
			 // delete generated files
			 new File(adiFileName).delete();
			 new File("out/movie-cerlopes-" + value +".ts").delete();
			 new File("out/POSTER_CERLOPES_TEST_000"+value+"_COVER1.jpg").delete();
			 new File("out/POSTER_CERLOPES_TEST_000"+value+"_COVER2.jpg").delete();
			 new File("out/poster-cerlopes-"+value+".jpg").delete();
			 new File("out/preview-cerlopes-"+value+".sent").delete();
			 new File("out/preview-cerlopes-"+value+".ts").delete();
			 new File("out/preview-cerlopes-"+value+".ts.sent").delete();
			 System.out.println("Deleting generated files . . .");
			 index++;
		}
		
	}

	private static void copyFileToFolder(String fileName, String folder) throws IOException
	{
		FileUtils.copyFileToDirectory(new File(fileName), new File(folder));
	}
	
}
