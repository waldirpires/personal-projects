package com.ericsson.cms.orng.load.scripts.extract.grv


def filename = "C:\\share\\mt9-675\\MT9-675-sim\\"+"sim-jmx.log"
def datetime
boolean nextLine=false
int numLine = 0
Scanner sc = null;
int i = 0;
def start = "Mon Dec  2 18:50:00 CET 2013"
def end =   "Tue Dec  3 07:00:00 CET 2013"
def max = -1;
def objName = 
"Name: java.lang:type=Memory"
//"Name: Catalina:j2eeType=Servlet,name=EnablersServlet,WebModule=//localhost/orng-sim-ws,J2EEApplication=none,J2EEServer=none" 
//"Name: Catalina:j2eeType=Servlet,name=RightvServlet,WebModule=//localhost/orng-sim-ws,J2EEApplication=none,J2EEServer=none" 
//"Name: Catalina:j2eeType=Servlet,name=CDNServlet,WebModule=//localhost/orng-sim-ws,J2EEApplication=none,J2EEServer=none"
def file = new File(filename)
new File(filename).eachLine { line ->
	
	//println(line)
	if (line.contains("CET"))
	{
		datetime = line
	}
	//requestCount
	//errorCount
	if (line.equals(objName))
	{
		nextLine = true
	}
	// maxTime
	// requestCount
	// PeakThreadCount
	// TotalStartedThreadCount
	// ThreadCount
	// CurrentThreadCpuTime
	// maxInstances
//	if (line.startsWith("singleThreadModel"))
//	{
//		nextLine = false
//	}
	
	if (line.startsWith("HeapMemoryUsage:") && nextLine)
	{
		nextLine = false
		List<String> list = line.tokenize()
		println((i+1) + "\t" + list.get(list.size()-1))
	}

	//FreePhysicalMemorySize
	//ProcessCpuLoad:
	//SystemCpuLoad
//	if (line.contains("processingTime") && line.indexOf("processingTime: 0") == -1)
//	{
//		println(line)
//	}
	
//	if (nextLine && line.contains("processingTime") && line.indexOf("processingTime: 0") == -1)
//	{
//		nextLine = false
//		println(line)
//	}
	
//	if (nextLine && line.contains("maxTime") && line.indexOf("maxTime: 0") == -1)
//	{
//		//println(line)
//		switch(numLine)
//		{
//			case 0: 
//			break
//		}
//		numLine++
//	}
//
//	if (line.contains("Name: Catalina:j2eeType=Servlet,name=RightvServlet,WebModule=//localhost/orng-sim-ws,J2EEApplication=none,J2EEServer=none"))
//	{
//		nextLine = true
//	}
	i++
}