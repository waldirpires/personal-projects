

package com.ericsson.cms.espn.dao;

import grails.test.mixin.*
import groovy.sql.Sql

import org.apache.commons.logging.LogFactory
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import com.ericsson.cms.espn.entity.Mso
import com.ericsson.cms.espn.entity.MsoOverride
import com.ericsson.cms.espn.entity.Sites

class SitesDaoTest {

	def sql
	private static final log = LogFactory.getLog(this)
	
	@Before
	public void setUp() throws Exception {
		log.info "Connecting to DB server . . ."
		sql = Sql.newInstance("jdbc:oracle:thin:@localhost:51521/ttv", "wfs",
                      "Wf\$1234", "oracle.jdbc.OracleDriver")
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSites() {
		String query = "Select ID_, NAME from SITES_SITE WHERE TYPE=(SELECT  ID_ FROM sites_sitetype WHERE TYPE='MEDIAPATH')"
		def rows = sql.rows(query)

		def sitesList = []
		
		rows.each { row ->
			def sites = new Sites(ID: row.ID_, name: row.name)
			sitesList.add(sites)
		}
		sql.close()
			
		log.info sitesList
		Assert.assertTrue(!sitesList.isEmpty());
	}
	
	@Test
	public void testGetMsosFromSite()
	{
		def siteId = "5"
		//Select MSO_ID, MSO_NAME from SITES_MSO WHERE SITE_ID=?
		log.info "GetMsosFromSite from service"
		String query = "Select MSO_ID, MSO_NAME from SITES_MSO WHERE SITE_ID=${siteId}"
		def rows = sql.rows(query)

		def msoList = []
		
		rows.each { row ->
			def mso = new Mso(id: row.MSO_ID, name: row.MSO_NAME)
			msoList.add(mso)
		}
		
		log.info "GetMsosFromSite from service: retrieved " + msoList.size() + " elements" 
		
		for (mso in msoList)
		{
			mso.msoOverrides = getMsoOverridesFromMso(mso.id)
		}
		sql.close()
		
		println msoList
	}

	def getMsoOverridesFromMso(msoId)
	{
		log.info "getMsoOverridesFromMso from service"
		String query = "Select OVERRIDE_ID, FIELD_XPATH, SEARCH_VALUE, REPLACEMENT_VALUE from SITES_MSO_OVERRIDE WHERE MSO_ID='${msoId}'"
		def rows = sql.rows(query)

		def msoOverrideList = []
		
		rows.each { row ->
			def msoOverride = new MsoOverride(ID: row.OVERRIDE_ID, fieldPath: row.FIELD_XPATH, searchValue: row.SEARCH_VALUE, replacementValue: row.REPLACEMENT_VALUE)
			msoOverrideList.add(mso)
		}
		
		log.info "getMsoOverridesFromMso from service: retrieved " + msoOverrideList.size() + " elements"	
		return msoOverrideList

	}

}
