package com.ericsson.cms.espn.entity

class SitesMsos {

	String id
	String siteName
	String numberOfMsos
	String description
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[%s] %s/%s: %s", id, siteName, description, numberOfMsos)
	}
}
