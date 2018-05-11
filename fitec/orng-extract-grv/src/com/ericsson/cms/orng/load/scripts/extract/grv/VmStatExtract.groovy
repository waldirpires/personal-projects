package com.ericsson.cms.orng.load.scripts.extract.grv


def filename = "C:\\share\\mt8\\MT8-sim\\vmstat.log"
def datetime
boolean nextLine=false
Scanner sc = null;
int i = 0;
new File(filename).eachLine { line ->
	
	
	if (line.contains("CET"))
	{
		datetime = line
	}
	if (nextLine)
	{
		sc = new Scanner(line);
		nextLine = false

		sc.nextInt() //r
		sc.nextInt() //b
		int swpd = sc.nextInt() //swpd
		int free = sc.nextInt() //free
		int buff = sc.nextInt() //buff
		int cache = sc.nextInt() //cache
				
		println(i+"\t"+datetime + "\t" + swpd + "\t" + free + "\t" + buff + "\t" + cache)
		// + "\t" + split[pos+1] + "\t" + split[pos+2] + "\t" + split[pos+3]);
//		for (int i = 0; i < split.length; i++)
//		{
//			println(i + "\t" + split[i]);
//		}
	//	println(datetime + "\t" + iddle)
		i++
	}
	if (line.contains("swpd"))
	{
		nextLine = true
	}
}