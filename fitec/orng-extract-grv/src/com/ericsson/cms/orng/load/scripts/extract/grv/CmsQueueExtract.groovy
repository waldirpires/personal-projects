package com.ericsson.cms.orng.load.scripts.extract.grv

def filename = "C:\\share\\mt9-675\\mt9-675-cms\\cms-queue.log"
//def filename = "C:\\share\\cms_queue_report_mt_2013-11-22.txt"
def datetime
int i = 0
int j = 0
def start = "Mon Dec  2 18:50:00 CET 2013"
def end =   "Tue Dec  3 07:00:00 CET 2013"
new File(filename).eachLine { line ->
	
	if (line.contains("CET"))
	{
		datetime = line
	}	
	if (datetime.compareTo(start)>=0 && datetime.compareTo(end)<=0)
	{
		if (!line.contains("Mon") && !line.contains("Tue") && !line.contains("Content")
			&& !line.contains("Heavy") && !line.contains("--") && !line.contains("M1CMSAS1"))
		{
			println(j + "\t" + i + "\t" + datetime + "\t" + line.split(" ")[0])
			j++
		}
	}

	//}
	i++
}