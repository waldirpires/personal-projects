package com.ericsson.cms.espn.domain

class SiteGroupCopy {

	String srcSiteId
	String destSiteId
	
    static constraints = {
		srcSiteId nullable:false 
		destSiteId nullable:false		
    }
}
