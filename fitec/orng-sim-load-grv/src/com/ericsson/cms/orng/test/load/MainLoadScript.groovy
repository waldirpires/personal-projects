package com.ericsson.cms.orng.test.load;

import java.util.concurrent.TimeUnit

import org.apache.log4j.*

import com.ericsson.cms.orng.test.util.SystemUtil;
import com.ericsson.cms.orng.test.util.TimeUtils

Logger log = Logger.getLogger(MainLoadScript.class)

SystemUtil.showSystemInfo()

def properties = new Properties();
new File(OrngLoadTestConstants.ORNG_SIM_LOAD_PROPERTIES_FILE).withInputStream { stream ->
	properties.load(stream)
}

log.info "Loaded properties: " + properties.keySet()
log.info properties

def config = new LoadScriptConfig()
config.loadFromProperties(properties)

log.info "starting load loop . . ."
def window = config.getCurrentWindow()

def ingestProcessor = new PackageIngestProcessor(config: config)
int index = Integer.parseInt config.getConfigProperty("load.index.start")
int limit = 0
if (config.getConfigProperty("load.index.limit") == null ||
	config.getConfigProperty("load.index.limit").trim().equals(""))
{
	limit = -1
} else 
{
	limit = config.getConfigProperty("load.index.limit")!=null?Integer.parseInt( config.getConfigProperty("load.index.limit")):-1
}

if (window)
{
	def loadStats = new LoadScriptStats(config: config)
	loadStats.updateWindow(window)
	loadStats.startLoadTest()
	
	while (limit == -1 || index <=limit)
	{
		log.info "Loading index " + index
		// check time to see if there are window changes
		if (!config.getCurrentWindow().equals(window))
		{
			log.info "window change detected!"
			window = config.getCurrentWindow()
			loadStats.updateWindow(window)
		}
		loadStats.startIngest()
		// select the package to ingest
		def selectedPackage = config.choosePackageRandomly()
		log.info "INGEST: selectedPackage: ${selectedPackage} | number of ingests: ${loadStats.numIngests}"
		// execute ingest to WF
		ingestProcessor.startIngestProcess(0, 0)
		// sleep
		TimeUtils.sleep(loadStats.sleepTime)
		loadStats.stopIngest()
		if (loadStats.totalTime > config.totalTime)
		{
			break
		}
		index++
	}
	loadStats.stopLoadTest()
}

private showSystemInfo(Logger log) {
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
		log.info "File system root: " + root.getAbsolutePath();
		log.info "Total space (bytes): " + root.getTotalSpace();
		log.info "Free space (bytes): " + root.getFreeSpace();
		log.info "Usable space (bytes): " + root.getUsableSpace();
	}
}
