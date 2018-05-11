package com.ericsson.cms.orng.test.load

class LoadWindowConfig {

	int id
	int rate
	int startTime
	int finishTime
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%dh -> %dh / %d i/s", startTime, finishTime, rate)
	}
}
