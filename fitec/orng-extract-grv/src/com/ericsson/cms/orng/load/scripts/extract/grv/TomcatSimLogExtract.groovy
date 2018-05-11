package com.ericsson.cms.orng.load.scripts.extract.grv


def filename = "C:\\temp\\SIM\\mt8\\mt8-sim\\sim-count2.log"
def datetime

new File(filename).eachLine { line ->
	
	if (line.contains("CET"))
	{
		datetime = line
	}
	if (line.contains("cdn.op.getAssetDistributionStatus"))
	{
		println(datetime + "\t" + line.split(" ")[line.split(" ").length-1]);
	}
}

