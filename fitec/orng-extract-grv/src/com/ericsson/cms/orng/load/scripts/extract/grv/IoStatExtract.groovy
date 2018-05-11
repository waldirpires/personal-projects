package com.ericsson.cms.orng.load.scripts.extract.grv


def filename = "C:\\temp\\SIM\\mt8\\mt8-sim\\iostat.log"
def datetime
boolean nextLine=false
new File(filename).eachLine { line ->
	
	if (line.contains("CET"))
	{
		datetime = line
	}
	if (nextLine)
	{
		nextLine = false
		def iddle = line.split(" ")[line.split(" ").length-1]
		println(datetime + "\t" + iddle)
	}
	if (line.contains("avg-cpu"))
	{
		nextLine = true
	}	
}