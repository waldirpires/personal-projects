package com.ericsson.cms.orng.load.scripts.extract.grv

def filename = "C:\\share\\mt14c\\" + "df.log.partial.log"
def datetime
def start = "Mon Dec  2 18:50:00 CET 2013"
def end =   "Tue Dec  3 07:00:00 CET 2013"
new File(filename).eachLine { line ->
	
	if (line.contains("CET"))
	{
		datetime = line
	}
//	if (datetime.compareTo(start)>=0 && datetime.compareTo(end)<=0)
//	{
//		if (line.contains("Average:"))
//		{
//			line = line.replace(".", ",")
//			println(datetime + "\t" + line.split(" ")[line.split(" ").length-1])
//			//println(datetime + "\t" + line.split(" ")[14])
////		for (int i = 0; i < line.split(" ").length; i++)
////		{
////			println(i + "\t\t" + line.split(" ")[i]);
////		}
//		}
//	}
	if (line.contains("/var") || line.contains("/opt"))
	{
		println datetime + "\t" + line
	}
}