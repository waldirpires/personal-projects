package com.ericsson.cms.orng.test.load

import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

import javax.management.MBeanServerConnection

import org.apache.log4j.Logger

import com.ericsson.cms.orng.test.util.TimeUtils

class LoadScriptStats {

	private static final Logger log = Logger.getLogger(LoadScriptStats.class)
	
	long startIngest
	long ingestTime
	int numIngests = 0
	long startTime	
	def config
	long totalNumIngests
	long totalTime
	long sleepTime
	
	def startLoadTest()
	{
		startTime = System.currentTimeMillis()
		totalTime = 0
		log.info "Start time: ${startTime}"		
	}
	
	def startIngest()
	{
		startIngest = System.currentTimeMillis()
		numIngests ++
	}
	
	def stopIngest()
	{
		ingestTime = System.currentTimeMillis() - startIngest
		totalTime += ingestTime
		showStats()
		//printUsage()
	}

	private showStats() {
		log.info "${numIngests} ====================================================================================="
		log.info "Ingest time: ${ingestTime} ms - " + TimeUtils.formatTime(ingestTime)
		log.info "Running time: ${totalTime} ms - " + TimeUtils.formatTime(totalTime) + " - config: " +
		  config.totalTime + " ms - " + TimeUtils.formatTime(config.totalTime)		
		log.info String.format("Percentage: %3.2f", getPercentage())		
		log.info "Rate -> real: " + getNumIngestsPerMinute() + " i/m | config: " + config.getCurrentWindow().rate
		log.info "Number of ingests ${numIngests} - perc: " + (numIngests / totalNumIngests * 100)
	}
	
	def stopLoadTest()
	{
		totalTime = System.currentTimeMillis() - startTime
		log.info "FINISHED"
		showStats()
	}
	
	def getNumIngestsPerMinute()
	{
		if (totalTime > 0)
			return numIngests / (totalTime / 1000 / 60)
	}
	
	def getExpectedRateIngestsPerMinute()
	{
		return totalNumIngests/TimeUnit.MILLISECONDS.toMinutes(config.totalTime)
	}
	
	def getPercentage()
	{
		def perc = (totalTime/(float)config.totalTime * 100)
		if (perc > 100)
			return 100.0
		else
			return perc
	}
	
	def printUsage() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.getName().startsWith("get")
			&& Modifier.isPublic(method.getModifiers())) {
				Object value;
				try {
					value = method.invoke(operatingSystemMXBean);
				} catch (Exception e) {
					value = e;
				} // try
				System.out.println(method.getName() + " = " + value);
			} // if
		} // for
		getPercentageCpu()
	}
	
	def getPercentageCpu()
	{
		MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
		
		OperatingSystemMXBean osMBean = ManagementFactory.getOperatingSystemMXBean();
		
		long nanoBefore = System.nanoTime();
		long cpuBefore = osMBean.getProcessCpuTime();
		
		// Call an expensive task, or sleep if you are monitoring a remote process
		
		long cpuAfter = osMBean.getProcessCpuTime();
		long nanoAfter = System.nanoTime();
		
		long percent;
		if (nanoAfter > nanoBefore)
		 percent = ((cpuAfter-cpuBefore)*100L)/
		   (nanoAfter-nanoBefore);
		else percent = 0;
		
		System.out.println("Cpu usage: "+percent+"%");
	}

	def updateWindow(window)
	{
		sleepTime = TimeUnit.MINUTES.toMillis(1) / window.rate // frequency = rate / minute
		log.info "Sleep time: " + sleepTime + " ms"
		totalNumIngests = config.totalTime/sleepTime
		log.info "Number of ingests (expected): " + (totalNumIngests) + " | -> i/m: " + getExpectedRateIngestsPerMinute()	
	}
		
}
