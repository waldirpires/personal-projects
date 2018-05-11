package com.ericsson.msoimport.domain;

public class Site {
	public int Id;
	public String Name;
	public int numNsos;

	@Override
	public String toString() {
		return String.format("%3d - %30s: [%3d]", Id, Name, numNsos);
	}
}
