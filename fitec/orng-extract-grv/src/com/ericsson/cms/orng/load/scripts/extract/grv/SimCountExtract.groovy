package com.ericsson.cms.orng.load.scripts.extract.grv


def filename = "C:\\temp\\SIM\\mt8\\mt8-sim\\sim-count2.log"
def datetime
boolean nextLine=false
new File(filename).eachLine { line ->
	
	if (line.contains("CET"))
	{
		datetime = line
	}
	if (line.contains("righttvnull.requests.delay.number:"))
	{
		println(datetime + "\t" + line.split(" ")[line.split(" ").length-1]);
	}
}
