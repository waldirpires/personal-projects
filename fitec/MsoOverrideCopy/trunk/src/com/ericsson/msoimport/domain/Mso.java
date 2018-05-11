package com.ericsson.msoimport.domain;

import java.util.ArrayList;
import java.util.List;

public class Mso {
	public int Id;
	public String Name;
	public List<MsoOverride> msoOverrides;

	public Mso(int Id, String Name) {
		this.Id = Id;
		this.Name = Name;
		msoOverrides = new ArrayList<MsoOverride>();
	}

	@Override
	public String toString() {
		return String.format("%d - %s (%d)", Id, Name, msoOverrides.size());
	}

}
