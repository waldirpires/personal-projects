package msooverridecopy

import grails.transaction.Transactional
import groovy.sql.Sql

import com.ericsson.cms.espn.domain.SiteGroupCopy
import com.ericsson.cms.espn.entity.Mso
import com.ericsson.cms.espn.entity.MsoOverride
import com.ericsson.cms.espn.entity.Sites
import com.ericsson.cms.espn.entity.SitesMsos

@Transactional
class SitesService {

	def dataSource
	
	def getSql()
	{
		//def sql = new Sql(dataSource)
		def sql = Sql.newInstance("jdbc:oracle:thin:@localhost:51521/ttv", "wfs",
                      "Wf\$1234", "oracle.jdbc.OracleDriver")
	}
	
    def getSites() {

		def sql = new Sql(dataSource)
		log.info "getSites()"
		String query = "Select ID_, NAME from SITES_SITE WHERE TYPE=(SELECT  ID_ FROM sites_sitetype WHERE TYPE='MEDIAPATH')"
		def rows = sql.rows(query)

		def sitesList = []
		
		rows.each { row ->
			def sites = new Sites(ID: row.ID_, name: row.name)
			sitesList.add(sites)
		}
		sql.close()
		log.info "Returned " + sitesList.size() + " sites: " + sitesList
		
		return sitesList
    }
	
	def getNumberOfMsosForSites()
	{
		def sql = new Sql(dataSource)
		log.info "getNumberOfMsosForSites()"
		def query = """
			SELECT site.id_ as id, site.name as sitename, site.description, COUNT(mso.mso_id) AS numbermsos FROM sites_mso mso
			RIGHT JOIN sites_site site
			ON mso.site_id = site.id_
			where site.TYPE=(SELECT  ID_ FROM sites_sitetype WHERE TYPE='MEDIAPATH')
			GROUP BY site.id_, site.name, site.description
		"""

		def rows = sql.rows(query)
		
		def sitesList = []
		
		rows.each { row ->
			def sitesMsos = new SitesMsos(id: row.id, siteName: row.sitename, numberOfMsos: row.numbermsos, 
				description: row.description)
			sitesList.add(sitesMsos)
		}

		log.info "Returned " + sitesList.size() + " sites total: " + sitesList 
		return sitesList	
	}
	
	def countMsosFromSite(siteId)
	{
		log.info "countMsosFromSite()"
		log.info "Counting MSOs from site " + siteId
		def sql = new Sql(dataSource)
		String query = "Select count(*) as count from SITES_MSO WHERE SITE_ID=${siteId}"
		def rows = sql.rows(query)

		int num = rows.size()
		log.info "Counting MSOs from site " + siteId + ": " + num
		return num
	}
	
	def getMso(siteId, msoName)
	{
		def sql = new Sql(dataSource)
		log.info "getMso()"
		String query = "Select MSO_ID, MSO_NAME from SITES_MSO WHERE MSO_NAME='${msoName}' AND SITE_ID=${siteId}"
		def rows = sql.rows(query)

		def msoList = []
		
		rows.each { row ->
			def mso = new Mso(id: row.MSO_ID, name: row.MSO_NAME)
			msoList.add(mso)
		}
		sql.close()
		assert msoList.size() <= 1
		log.info "Returned " + msoList.size() == 1?msoList[0]:null
		return msoList.size() == 1?msoList[0]:null

	}
	
	def getMsoOverridesFromMso(msoId)
	{
		def sql = new Sql(dataSource)
		log.info "getMsoOverridesFromMso() from service"
		String query = "Select OVERRIDE_ID, FIELD_XPATH, SEARCH_VALUE, REPLACEMENT_VALUE from SITES_MSO_OVERRIDE WHERE MSO_ID='${msoId}'"
		def rows = sql.rows(query)

		def msoOverrideList = []
		
		rows.each { row ->
			def msoOverride = new MsoOverride(ID: row.OVERRIDE_ID, fieldXPath: row.FIELD_XPATH, searchValue: row.SEARCH_VALUE, replacementValue: row.REPLACEMENT_VALUE)
			msoOverrideList.add(msoOverride)
		}
		sql.close()
		log.info "Returned " + msoOverrideList.size() + " MSO overrides: " + msoOverrideList
		return msoOverrideList

	}
	
	def getMsosFromSite(siteId)
	{
		//Select MSO_ID, MSO_NAME from SITES_MSO WHERE SITE_ID=?
		def sql = new Sql(dataSource)
		log.info "getMsosFromSite() from service: " + siteId
		String query = "Select MSO_ID, MSO_NAME from SITES_MSO WHERE SITE_ID=${siteId}"
		def rows = sql.rows(query)

		def msoList = []
		
		rows.each { row ->
			def mso = new Mso(id: row.MSO_ID, name: row.MSO_NAME)
			msoList.add(mso)
		}
		
		for (mso in msoList)
		{
			mso.msoOverrides = getMsoOverridesFromMso(mso.id)
		}
		sql.close()
		log.info "Returned " + msoList.size() + " MSOs: " + msoList
		return msoList
	}
	
	def getSite(siteId)
	{
		def sql = new Sql(dataSource)
		log.info "getSite() from service: " + siteId
		String query = "Select ID_, NAME from SITES_SITE WHERE ID_="+siteId
		def rows = sql.rows(query)

		def sitesList = []
		
		rows.each { row ->
			def sites = new Sites(ID: row.ID_, name: row.name)
			sitesList.add(sites)
		}
		sql.close()
		log.info "Returned site " + sitesList[0] 
		return sitesList[0]

	}
	
	def executeSiteGroupCopy(SiteGroupCopy siteGroupCopyInstance)
	{
		log.info "Executing Site Group Copy"
		// get all sites from src
		def srcSites = getSite(siteGroupCopyInstance.srcSiteId)
		// get all MSOs from site
		def msoSites = getMsosFromSite(siteGroupCopyInstance.srcSiteId)
		// for each MSO
		checkSequence("WFS.SITES_MSO_SEQ", "WFS.SITES_MSO", "MSO_ID");
		checkSequence("WFS.SITES_MSO_OVERRIDE_SEQ", "WFS.SITES_MSO_OVERRIDE", "OVERRIDE_ID");
		checkSequence("WFS.SITES_MSO_OVERRIDE_HISTORY_SEQ", "WFS.SITES_MSO_OVERRIDE_HISTORY", "OVERRIDE_HISTORY_ID");

		for (mso in msoSites)
		{
			if (getMso(siteGroupCopyInstance.destSiteId, mso.name) != null)
			{
				// delete MSO from destination
				deleteMso(siteGroupCopyInstance.destSiteId, mso)
			}
			// insert MSO in new SITE MSO
			def msoId = insertMso(siteGroupCopyInstance.destSiteId, mso)
			// get ID of new MSO
			// if mso contains msooverrieds
			if (mso.msoOverrides != null && !mso.msoOverrides.isEmpty())
			{
				// insert mso override for new mso
				for (msoOverride in mso.msoOverrides)
				{
					insertMsoOverride(msoId, msoOverride)
				}
			}
		}
	}

	def insertMso(siteId, mso) throws Exception {
		log.debug "Inserting " + mso;
		String query = "Insert into WFS.SITES_MSO (MSO_NAME,SITE_ID) values ('${mso.name}', ${siteId})";
		def sql = new Sql(dataSource)
		sql.execute(query)
		
		mso = getMso(siteId, mso.name)
		sql.close()
		return mso.id;
	}

	def checkSequence(String sequence, String table, String idColumn) throws Exception {
		log.info "Checking sequence ${sequence} for table ${table}.${idColumn}"
		int maxId = selectMaxId(table, idColumn);
		int currVal = selectSequenceNextVal(sequence);
		if (currVal < maxId)
			updateSequence(sequence, maxId - currVal + 1);
		else
			updateSequence(sequence, 1);
	}

	
	private int updateSequence(String sequence, int newValue) throws Exception {
		log.debug "Updating sequence " + sequence + " to increment by " + newValue
		def query = "alter sequence ${sequence} increment by ${newValue}".toString()
		def sql = new Sql(dataSource)
		sql.execute(query)
		sql.close()
		
		return newValue
	}

	private int selectMaxId(final String table, final String idColumn) throws Exception {
		log.debug "Selecting max(${table}.${idColumn})";
		String query = "select max(${idColumn}) as ID FROM ${table}";

		def sql = new Sql(dataSource)
		def rows = sql.rows(query)
		
		def maxId = -1
		rows.each { row ->
			maxId = row.ID
		}

		sql.close()
		log.info "selectMaxId: ${maxId}"
		return maxId!=null?maxId:-1;
	}

	private int selectSequenceNextVal(final String sequence) throws Exception {
		log.debug "Selecting " + sequence + ".nextval";
		String query = "select ${sequence}.nextval as val from dual";
		def sql = new Sql(dataSource)
		def rows = sql.rows(query)

		def nextVal = -1
		rows.each { row ->
			nextVal = row.val
		}

		sql.close()
		log.info "selectSequenceNextVal: ${nextVal}"
		return nextVal
		
	}

	def deleteMso(String siteId, Mso mso) throws Exception {
		log.debug "Deleting " + mso
		deleteMsoOverrides(siteId, mso)
		deleteMsoOverridesHistory(siteId, mso)
		String query = "Delete WFS.SITES_MSO WHERE (MSO_NAME='${mso.name}' AND SITE_ID=${siteId})"
		def sql = new Sql(dataSource)
		sql.execute(query)
		
		query = "select * from WFS.SITES_MSO WHERE (MSO_NAME='${mso.name}' AND SITE_ID=${siteId})"
		def rows = sql.rows(query)
		sql.close()
		return rows.size == 0
	}

	def deleteMsoOverrides(String siteId, Mso mso) throws Exception {
		log.debug "Deleting MSO Overrides of " + mso;
		String query = "DELETE SITES_MSO_OVERRIDE where MSO_ID = (Select MSO_ID from SITES_MSO WHERE MSO_NAME='${mso.name}' and SITE_ID=${siteId})";
		def sql = new Sql(dataSource)
		sql.execute(query)
		
		query = "select * from SITES_MSO_OVERRIDE where MSO_ID = (Select MSO_ID from SITES_MSO WHERE MSO_NAME='${mso.name}' and SITE_ID=${siteId})"
		def rows = sql.rows(query)
		return rows.size == 0
	}

	def deleteMsoOverridesHistory(String siteId, Mso mso) throws Exception {
		log.debug "Deleting MSO Overrides History of " + mso
		String query = "DELETE SITES_MSO_OVERRIDE_HISTORY where MSO_ID = (Select MSO_ID from SITES_MSO WHERE MSO_NAME='${mso.name}' and SITE_ID=${siteId})"
		def sql = new Sql(dataSource)
		sql.execute(query)
		
		query = "select * from SITES_MSO_OVERRIDE_HISTORY where MSO_ID = (Select MSO_ID from SITES_MSO WHERE MSO_NAME='${mso.name}' and SITE_ID=${siteId})"
		def rows = sql.rows(query)
		sql.close()
		return rows.size == 0
	}

	def insertMsoOverride(String msoId, MsoOverride msoOverride) throws Exception {
		log.debug "Inserting " + msoOverride
		String query = "Insert into WFS.SITES_MSO_OVERRIDE (MSO_ID, FIELD_XPATH, SEARCH_VALUE, REPLACEMENT_VALUE) values (${msoId}, '${msoOverride.fieldXPath}', '${msoOverride.searchValue}', '${msoOverride.replacementValue}')".toString()
		def sql = new Sql(dataSource)
		sql.execute(query)

		query = "select * from WFS.SITES_MSO_OVERRIDE where MSO_ID=${msoId} and FIELD_XPATH='${msoOverride.fieldXPath}' and SEARCH_VALUE = '${msoOverride.searchValue}' and REPLACEMENT_VALUE = '${msoOverride.replacementValue}'".toString()
		def rows = sql.rows(query)

		//assert rows.size() == 1
		def msoOverrideId = -1
		rows.each { row ->
			msoOverrideId = row.OVERRIDE_ID
		}

		if (msoOverrideId == -1)
			logger.warn("Result: " + result + " for inserting " + msoOverride)
		
		sql.close()			
		return msoOverrideId
	}


}
