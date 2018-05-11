package com.ericsson.msoimport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.ericsson.msoimport.DatabaseAccess.ResultSetProcessor;
import com.ericsson.msoimport.controller.LongProcess;
import com.ericsson.msoimport.domain.Mso;
import com.ericsson.msoimport.domain.MsoOverride;
import com.ericsson.msoimport.utils.CopyResultStats;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);

	DatabaseAccess databaseAccess = new DatabaseAccess();

	private Thread thread;
	private CopyResultStats stats;
	private LongProcess lp;
	
	static {
		LogAppender.addAppender(logger);
	}

	public Main(LongProcess lp) {
		this.lp = lp;
	}

	public void msoImportToSite(final String fileName, final int siteId) throws Exception {
		logger.info("Starting import...");
		try {
			databaseAccess.connect(Constants.neptuneXML);
			if (thread != null)
				thread.interrupt();
			(thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						doImport(siteId, fileName);
					} catch (Exception e) {
						logger.warn(e);
					}
					thread = null;
				}

			})).start();
			thread.join();
		} finally {
			databaseAccess.disconnect();
		}
		logger.info("Done!");
	}

	public CopyResultStats msoCopyToSite(final String srcSiteId, final String destSiteId) throws Exception {
		logger.info("Starting import...");
		try {
			databaseAccess.connect(Constants.neptuneXML);
			lp.setProgress(20);
			if (thread != null)
				thread.interrupt();
			(thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						logger.info("Executing COPY");
						stats = doCopy(srcSiteId, destSiteId);
					} catch (Exception e) {
						logger.warn(e);
					}
					thread = null;
				}

			})).start();
			thread.join();
		} finally {
			databaseAccess.disconnect();
		}
		logger.info("Done!");
		return stats;
	}
	
	private void doImport(int siteId, String fileName) throws Exception {
		ReadInputScript readInputScript = new ReadInputScript(fileName);
		final List<Mso> list = readInputScript.readMso();
		logger.debug("Number of MSO found: " + list.size());
		checkSequence("WFS.SITES_MSO_SEQ", "WFS.SITES_MSO", "MSO_ID");
		checkSequence("WFS.SITES_MSO_OVERRIDE_SEQ", "WFS.SITES_MSO_OVERRIDE", "OVERRIDE_ID");
		checkSequence("WFS.SITES_MSO_OVERRIDE_HISTORY_SEQ", "WFS.SITES_MSO_OVERRIDE_HISTORY", "OVERRIDE_HISTORY_ID");
		for (Mso mso : list) {
			deleteMso(siteId, mso);
			int result = insertMso(siteId, mso);
			if (result == 0) {
				logger.warn("Cannot insert " + mso);
				continue;
			}
			int msoId = selectMsoId(siteId, mso);
			for (MsoOverride msoOverride : mso.msoOverrides) {
				result = insertMsoOverride(msoId, msoOverride);
				if (result == 0) {
					logger.warn("Cannot insert " + msoOverride);
					continue;
				}
			}
		}
	}

	private CopyResultStats doCopy(String srcSiteId, String destSiteId) throws Exception {
		
		CopyResultStats stats = new CopyResultStats();
		
		final List<Mso> list = selectMsosFromSite(srcSiteId);
		stats.setNumMsosFromSite(list.size());
		stats.setNumMsosToSite(selectMsosFromSite(destSiteId).size());		
		logger.info("Number of MSO found: " + list.size());
		checkSequence("WFS.SITES_MSO_SEQ", "WFS.SITES_MSO", "MSO_ID");
		checkSequence("WFS.SITES_MSO_OVERRIDE_SEQ", "WFS.SITES_MSO_OVERRIDE", "OVERRIDE_ID");
		checkSequence("WFS.SITES_MSO_OVERRIDE_HISTORY_SEQ", "WFS.SITES_MSO_OVERRIDE_HISTORY", "OVERRIDE_HISTORY_ID");
		int remainder = 100 - lp.getProgress();
		int increment = remainder/list.size();
		for (Mso mso : list) {
			if (selectMsoId(Integer.parseInt(destSiteId), mso) != -1)
			{
				deleteMso(Integer.parseInt(destSiteId), mso);
				stats.addNumMsosDeletedFromDest();
			}
			int result = insertMso(Integer.parseInt(destSiteId), mso);
			if (result == 0) {
				logger.warn("Cannot insert " + mso);
				stats.addNumErrors();
				continue;
			}
			stats.addNumMsosCopied();
			int msoId = selectMsoId(Integer.parseInt(destSiteId), mso);
			if (mso.msoOverrides != null && !mso.msoOverrides.isEmpty())
			{
				for (MsoOverride msoOverride : mso.msoOverrides) {
					result = insertMsoOverride(msoId, msoOverride);
					if (result == 0) {
						logger.warn("Cannot insert " + msoOverride);
						stats.addNumErrors();
						continue;
					}
					stats.addNumMsoOverrideCopied();
				}
			}
			lp.setProgress(lp.getProgress() + increment);
		}
		stats.setNumMsosToSiteAfterCopy(selectMsosFromSite(destSiteId).size());
		return stats;
	}

	private int insertMsoOverride(int msoId, MsoOverride msoOverride) throws Exception {
		logger.debug("Inserting " + msoOverride);
		String[] params = new String[] { String.valueOf(msoId), msoOverride.getFieldXPath(), msoOverride.getSearchValue(), 
				msoOverride.getReplacementValue() };
		String insert = "Insert into WFS.SITES_MSO_OVERRIDE (MSO_ID, FIELD_XPATH, SEARCH_VALUE, REPLACEMENT_VALUE) values (?, '?', '?', '?')";
		int result = 1; 
				//databaseAccess.executeStatement(insert, params);
		if (result != 1)
			logger.warn("Result: " + result + " for inserting " + msoOverride);
		return result;
	}

	private int deleteMsoOverrides(int siteId, Mso mso) throws Exception {
		logger.debug("Deleting MSO Overrides of " + mso);
		String[] params = new String[] { mso.Name, String.valueOf(siteId) };
		String deleteStr = "DELETE SITES_MSO_OVERRIDE where MSO_ID = (Select MSO_ID from SITES_MSO WHERE MSO_NAME='?' and SITE_ID=?)";
		int result = 1; 
				//databaseAccess.executeStatement(deleteStr, params);
		logger.debug("Result: " + result);
		return result;
	}

	private int deleteMsoOverridesHistory(int siteId, Mso mso) throws Exception {
		logger.debug("Deleting MSO Overrides History of " + mso);
		String[] params = new String[] { mso.Name, String.valueOf(siteId) };
		String deleteStr = "DELETE SITES_MSO_OVERRIDE_HISTORY where MSO_ID = (Select MSO_ID from SITES_MSO WHERE MSO_NAME='?' and SITE_ID=?)";
		int result = 1; 
				//databaseAccess.executeStatement(deleteStr, params);
		logger.debug("Result: " + result);
		return result;
	}

	private int deleteMso(int siteId, Mso mso) throws Exception {
		logger.debug("Deleting " + mso);
		deleteMsoOverrides(siteId, mso);
		deleteMsoOverridesHistory(siteId, mso);
		String[] params = new String[] { mso.Name, String.valueOf(siteId) };
		String deleteStr = "Delete WFS.SITES_MSO WHERE (MSO_NAME='?' AND SITE_ID=?)";
		int result = 1; 
				//databaseAccess.executeStatement(deleteStr, params);
		return result;
	}

	private int insertMso(int siteId, Mso mso) throws Exception {
		logger.debug("Inserting " + mso);
		String[] params = new String[] { mso.Name, String.valueOf(siteId) };
		String insert = "Insert into WFS.SITES_MSO (MSO_NAME,SITE_ID) values ('?', ?)";
		int result = 1; 
				//databaseAccess.executeStatement(insert, params);
		return result;
	}

	private int selectMsoId(int siteId, Mso mso) throws Exception {
		logger.debug("Selecting " + mso);
		String[] params = new String[] { mso.Name, String.valueOf(siteId) };
		String selectMsoId = "Select MSO_ID from SITES_MSO WHERE MSO_NAME='?' AND SITE_ID=?";
		final int[] msoId = new int[] { -1 };

		databaseAccess.executeQuery(selectMsoId, params, new ResultSetProcessor() {

			@Override
			public void process(ResultSet rs) throws SQLException {
				if (rs.next()) {
					logger.debug("MSO_ID=" + rs.getInt(1));
					msoId[0] = rs.getInt(1);
				}
			}
		});

		return msoId[0];
	}

	private List<Mso> selectMsosFromSite(String siteId) throws Exception {
		logger.debug("Selecting MSOs from site ID " + siteId);
		String[] params = new String[] { siteId };
		String selectMsoId = "Select MSO_ID, MSO_NAME from SITES_MSO WHERE SITE_ID=?";

		final List<Mso> msos = new ArrayList<Mso>();
		databaseAccess.executeQuery(selectMsoId, params, new ResultSetProcessor() {

			@Override
			public void process(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Mso mso = new Mso(rs.getInt(1), rs.getString(2));
					msos.add(mso);
				}
			}
		});

		return msos;
	}

	private int selectSequenceNextVal(final String sequence) throws Exception {
		logger.debug("Selecting " + sequence + ".nextval");
		String[] params = new String[] { sequence };
		String selectMsoId = "select ?.nextval from dual";
		final int[] nextVal = new int[] { -1 };

		databaseAccess.executeQuery(selectMsoId, params, new ResultSetProcessor() {

			@Override
			public void process(ResultSet rs) throws SQLException {
				if (rs.next()) {
					logger.debug(sequence + ".nextval=" + rs.getInt(1));
					nextVal[0] = rs.getInt(1);
				}
			}
		});

		return nextVal[0];
	}

	private int selectMaxId(final String table, final String idColumn) throws Exception {
		logger.debug("Selecting max(" + table + "." + idColumn + ")");
		String[] params = new String[] { idColumn, table };
		String selectMsoId = "select max(?) FROM ?";
		final int[] msoId = new int[] { -1 };

		databaseAccess.executeQuery(selectMsoId, params, new ResultSetProcessor() {

			@Override
			public void process(ResultSet rs) throws SQLException {
				if (rs.next()) {
					logger.debug("max(" + table + "." + idColumn + "): " + rs.getInt(1));
					msoId[0] = rs.getInt(1);
				}
			}
		});

		return msoId[0];
	}

	private int updateSequence(String sequence, int newValue) throws Exception {
		logger.debug("Updating sequence " + sequence + " to increment by " + newValue);
		String[] params = new String[] { sequence, String.valueOf(newValue) };
		String insert = "alter sequence ? increment by ?";
		int result = databaseAccess.executeStatement(insert, params);
		logger.debug("Result: " + result);
		return result;
	}

	private void checkSequence(String sequence, String table, String idColumn) throws Exception {
		int maxId = selectMaxId(table, idColumn);
		int currVal = selectSequenceNextVal(sequence);
		if (currVal < maxId)
			updateSequence(sequence, maxId - currVal + 1);
		else
			updateSequence(sequence, 1);
	}

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		logger.debug("Starting...");
		logger.debug("Running...");
		logger.debug("Finishing...");
	}

}
