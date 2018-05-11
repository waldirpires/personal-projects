package com.ericsson.cms.orng.test.load

class LoadScriptConfigTest extends GroovyTestCase{

	def config
	
	void testloadPackageDistributionConfig()
	{
		config = new LoadScriptConfig()
		def properties = new Properties();
		properties.put("load.package.numPackages", "1")
		properties.put("load.package.selectionDistribution", "1.0")
		
		config.loadPackageDistributionConfig(properties)
		
		assert config.numPackages == 1
		println config.mapDistribution
	}
	
	void testloadPackageDistributionConfig2()
	{
		config = new LoadScriptConfig()
		def properties = new Properties();
		properties.put("load.package.numPackages", "2")
		properties.put("load.package.selectionDistribution", "0.2;0.8")
		
		config.loadPackageDistributionConfig(properties)
		
		assert config.numPackages == 2
		println config.mapDistribution
	}

	void testloadPackageDistributionConfig3()
	{
		config = new LoadScriptConfig()
		def properties = new Properties();
		properties.put("load.package.numPackages", "3")
		properties.put("load.package.selectionDistribution", "0.2;0.7;0.1")
		
		config.loadPackageDistributionConfig(properties)
		
		assert config.numPackages == 3
		println config.mapDistribution
	}

	void testGetSelectedPackage()
	{

		config = new LoadScriptConfig()
		def properties = new Properties();
		properties.put("load.package.numPackages", "3")
		properties.put("load.package.selectionDistribution", "0.2;0.7;1")
		
		config.loadPackageDistributionConfig(properties)

		assert config.getSelectedPackage(0.1) == 0
		assert config.getSelectedPackage(0.2) == 0
		assert config.getSelectedPackage(0.3) == 1
		assert config.getSelectedPackage(0.6) == 1
		assert config.getSelectedPackage(0.7) == 1
		assert config.getSelectedPackage(0.8) == 2
		assert config.getSelectedPackage(0.9) == 2
		assert config.getSelectedPackage(1.0) == 2
	}

	void testGetSelectedPackage2()
	{

		config = new LoadScriptConfig()
		def properties = new Properties();
		properties.put("load.package.numPackages", "4")
		properties.put("load.package.selectionDistribution", "0.2;0.6;0.8;1")
		
		config.loadPackageDistributionConfig(properties)

		assert config.getSelectedPackage(0.1) == 0
		assert config.getSelectedPackage(0.2) == 0
		assert config.getSelectedPackage(0.3) == 1
		assert config.getSelectedPackage(0.5999) == 1
		assert config.getSelectedPackage(0.6) == 1
		assert config.getSelectedPackage(0.699) == 2
		assert config.getSelectedPackage(0.7) == 2
		assert config.getSelectedPackage(0.7999) == 2
		assert config.getSelectedPackage(0.8) == 2
		assert config.getSelectedPackage(0.899) == 3
		assert config.getSelectedPackage(0.9) == 3
		assert config.getSelectedPackage(0.91) == 3
		assert config.getSelectedPackage(1.0) == 3
		assert config.getSelectedPackage(0.56553257) == 1
		
	}
	
	void testGetWindow()
	{
		config = new LoadScriptConfig()
		def properties = new Properties();
		properties.put("load.package.numPackages", "4")
		properties.put("load.package.selectionDistribution", "0.2;0.6;0.8;1")
		
		properties.put("load.window.size", "4")
		properties.put("load.window.times", "0:6;6:12;12:20;20:24")
		properties.put("load.window.rate", "2;6;10;4")
		properties.put("load.time.total", "2")
		
		config.loadFromProperties(properties)

		assert config.getWindow(2).id == 0
		assert config.getWindow(6).id == 1
		assert config.getWindow(7).id == 1
		assert config.getWindow(12).id == 2
		assert config.getWindow(13).id == 2
		assert config.getWindow(20).id == 3
		assert config.getWindow(21).id == 3
		assert config.getWindow(23).id == 3
		assert config.getWindow(0).id == 0
		
	}

}
