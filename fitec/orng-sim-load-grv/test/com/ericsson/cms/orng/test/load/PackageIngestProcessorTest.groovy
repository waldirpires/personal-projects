package com.ericsson.cms.orng.test.load

import static org.junit.Assert.*

import org.junit.Test


class PackageIngestProcessorTest{

	def ingestProcessor
	
	@Test
	void testOk()
	{
		println "ok"
	}
	
	@Test
	void testStartIngestProcess()
	{
		def properties = new Properties()
		properties.put(PackageIngestProcessor.CMS_WATCHFOLDER_PATH, "/content/watchFolder01")
		def config = new LoadScriptConfig()
		config.properties = properties
		ingestProcessor = new PackageIngestProcessor(config: config)
		assert ingestProcessor.config.properties.get(PackageIngestProcessor.CMS_WATCHFOLDER_PATH) != null		
		int index = 1;
		int selectedPackage = 0;
		assert ingestProcessor.startIngestProcess(index, selectedPackage)
		
	}
}
