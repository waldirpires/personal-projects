package com.ericsson.msoimport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ericsson.msoimport.DatabaseAccess.ResultSetProcessor;
import com.ericsson.msoimport.domain.Mso;
import com.ericsson.msoimport.domain.Site;

public class ReadSites {
	private static final Logger logger = Logger.getLogger(ReadSites.class);

	static {
		LogAppender.addAppender(logger);
	}

	public ReadSites() {
	}

	public List<Site> getSites() throws Exception {
		DatabaseAccess databaseAccess = new DatabaseAccess();
		try {
			databaseAccess.connect(Constants.neptuneXML);
			List<Site> sites = selectSites(databaseAccess);
			return sites;
		} finally {
			databaseAccess.disconnect();
		}
	}

	public Map<String, Site>  getSitesMap() throws Exception {
		DatabaseAccess databaseAccess = new DatabaseAccess();
		try {
			databaseAccess.connect(Constants.neptuneXML);
			return selectSitesMap(databaseAccess);
		} finally {
			databaseAccess.disconnect();
		}
	}
	
	private List<Site> selectSites(DatabaseAccess databaseAccess) throws Exception {
		logger.debug("Selecting sites");
		String[] params = new String[] {};
		String selectMsoId = "Select ID_, NAME from SITES_SITE WHERE TYPE=(SELECT  ID_ FROM sites_sitetype WHERE TYPE='MEDIAPATH')";
		final List<Site> sites = new ArrayList<Site>();

		databaseAccess.executeQuery(selectMsoId, params, new ResultSetProcessor() {

			@Override
			public void process(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Site site = new Site();
					site.Id = rs.getInt(1);
					site.Name = rs.getString(2);
					sites.add(site);
				}
			}
		});

		for (Site s: sites)
		{
			s.numNsos = selectMsosFromSite(String.valueOf(s.Id), databaseAccess).size();			
			logger.debug("Site=" + s);
		}
		
		return sites;
	}
	
	private List<Mso> selectMsosFromSite(String siteId, DatabaseAccess databaseAccess) throws Exception {
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
	
	public Map<String, Site> selectSitesMap(DatabaseAccess databaseAccess) throws Exception {
		logger.debug("Selecting sites");
		String[] params = new String[] {};
		String selectMsoId = "Select ID_, NAME from SITES_SITE WHERE TYPE=(SELECT  ID_ FROM sites_sitetype WHERE TYPE='MEDIAPATH')";
		final Map<String, Site> sites = new HashMap<String, Site>();

		databaseAccess.executeQuery(selectMsoId, params, new ResultSetProcessor() {

			@Override
			public void process(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Site site = new Site();
					site.Id = rs.getInt(1);
					site.Name = rs.getString(2);
					logger.debug("Site=" + site);
					sites.put(String.valueOf(site.Id), site);
				}
			}
		});

		return sites;
	}
	
}
