package com.ericsson.cms.espn.entity

class MsoOverride {
	
	String ID
	String fieldXPath
	String searchValue
	String replacementValue

	@Override
	public String toString() {
		return String.format("[%s] %s %s %s", ID, fieldXPath, searchValue, replacementValue)
	}

}
