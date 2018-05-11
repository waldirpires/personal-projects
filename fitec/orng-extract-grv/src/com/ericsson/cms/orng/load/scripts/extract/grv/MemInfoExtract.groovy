package com.ericsson.cms.orng.load.scripts.extract.grv


def filename = "C:\\share\\mt9-675\\MT9-675-sim\\" + "meminfo.log" 
//"C:\\share\\mt8\\MT8-db\\meminfo.log" 
//"C:\\temp\\SIM\\mt8\\mt8-sim\\meminfo.log"
def datetime
def start = "Mon Dec  2 18:50:00 CET 2013"
def end =   "Tue Dec  3 07:00:00 CET 2013"
new File(filename).eachLine { line ->
	
	if (line.contains("CET"))
	{
		datetime = line
	}
	if (datetime.compareTo(start)>=0 && datetime.compareTo(end)<=0)
	{
		if (line.contains("MemFree"))
		{
			println(datetime + "\t" + line.split(" ")[line.split(" ").length-2]);
		}
	}

}
