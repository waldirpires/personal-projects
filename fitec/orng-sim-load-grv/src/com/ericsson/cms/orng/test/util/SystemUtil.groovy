package com.ericsson.cms.orng.test.util

import org.apache.log4j.Logger

class SystemUtil {
	
	private static final Logger log = Logger.getLogger(SystemUtil.class)

	def static showSystemInfo() {
		log.info "ORNG Load Script"
		log.info "======================"
		log.info "OS information: " + System.getProperty("os.name") + "/ver. " + System.getProperty("os.version") + "/" + System.getProperty("os.arch")
		log.info ""
		log.info "JDK information: "
		log.info "JDK Home: " + System.getProperty("java.home")
		log.info "JDK Version: " + System.getProperty("java.version")
		log.info "JDK Classpath: " + System.getProperty("java.class.path")
		log.info "JDK Vendor: " + System.getProperty("java.vendor")
		
		/* Total number of processors or cores available to the JVM */
		log.info "Available processors (cores): " +
			Runtime.getRuntime().availableProcessors();
	
		/* Total amount of free memory available to the JVM */
		log.info "Free memory (bytes): " +
			Runtime.getRuntime().freeMemory();
	
		/* This will return Long.MAX_VALUE if there is no preset limit */
		long maxMemory = Runtime.getRuntime().maxMemory();
		/* Maximum amount of memory the JVM will attempt to use */
		log.info "Maximum memory (bytes): " +
			(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory);
	
		/* Total memory currently in use by the JVM */
		log.info "Total memory (bytes): " +
			Runtime.getRuntime().totalMemory();
		
		File[] roots = File.listRoots();
		for (File root : roots) {
			log.info "File system root: " + root.getAbsolutePath() + 
			" | Total space (Mbytes): " + root.getTotalSpace()/1024/1024 + 
			" | Free space (Mbytes): " + root.getFreeSpace()/1024/1024 + " | Usable space (Mbytes): " + root.getUsableSpace()/1024/1024;
		}
	}
	
	def static log(msg)
	{
		println getDateTimeStamp() + ": $msg"
	}
	
}
