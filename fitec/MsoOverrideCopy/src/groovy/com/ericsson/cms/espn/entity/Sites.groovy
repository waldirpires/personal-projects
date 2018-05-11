package com.ericsson.cms.espn.entity

import groovy.sql.Sql

import org.apache.commons.logging.LogFactory

class Sites {

	private static final log = LogFactory.getLog(this)
	
	String ID
	String name
	List<Mso> siteMsos = []
	int numMsos

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s", name)
	}	
	
	def static list()
	{
		log.info "Connecting to DB server . . ."
		def sql = Sql.newInstance("jdbc:oracle:thin:@localhost:51521/ttv", "wfs",
					  "Wf\$1234", "oracle.jdbc.OracleDriver")

		String query = "Select ID_, NAME from SITES_SITE WHERE TYPE=(SELECT  ID_ FROM sites_sitetype WHERE TYPE='MEDIAPATH')"
		def rows = sql.rows(query)

		def sitesList = []
		
		rows.each { row ->
			def sites = new Sites(ID: row.ID_, name: row.name)
			sitesList.add(sites)
		}
		sql.close()
		return sitesList

	}
	
}
