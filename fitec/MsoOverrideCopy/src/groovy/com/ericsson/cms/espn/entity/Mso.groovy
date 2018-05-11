package com.ericsson.cms.espn.entity

class Mso {

	String id
	String name
	List<MsoOverride> msoOverrides
	
	@Override
	public String toString() {
		return String.format("%s - %s (%d)", id, name, msoOverrides.size());
	}

}
