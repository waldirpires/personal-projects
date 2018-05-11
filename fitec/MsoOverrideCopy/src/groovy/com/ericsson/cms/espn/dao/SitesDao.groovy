package com.ericsson.cms.espn.dao

import groovy.sql.Sql

import com.ericsson.cms.espn.entity.Sites;

class SitesDao {

	def dataSource	
	
	def getSqlObj()
	{
		def sql = new Sql(dataSource)		
	}
	
	def getSites(sql)
	{
		String query = "Select ID_, NAME from SITES_SITE WHERE TYPE=(SELECT  ID_ FROM sites_sitetype WHERE TYPE='MEDIAPATH')"
		def rows = sql.rows(query)

		def sitesList = []
		
		rows.each { row ->
			def sites = new Sites(iD: row.ID_, name: row.name)
			sitesList.add(sites)
		}
		sql.close()
			
		return sitesList
	}
}
