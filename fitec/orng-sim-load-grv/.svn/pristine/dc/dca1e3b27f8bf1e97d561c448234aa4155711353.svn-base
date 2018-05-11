package com.ericsson.cms.orng.test.load

import org.apache.log4j.Logger

import com.ericsson.cms.orng.test.util.CalendarUtil

class LoadScriptConfig {

	private static final Logger log = Logger.getLogger(LoadScriptConfig.class)

	static final String LOAD_WINDOW_SIZE = "load.window.size"

	static final String LOAD_WINDOW_TIMES = "load.window.times"

	static final String LOAD_WINDOW_RATE = "load.window.rate"

	static final String LOAD_TIME_TOTAL = "load.time.total"

	static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

	static final String LOAD_PACKAGE_NUM_PACKAGES = "load.package.numPackages"

	static final String LOAD_PACKAGE_SELECTION_DISTRIBUTION = "load.package.selectionDistribution"
	
	def numPackages
	def windowSize
	def totalTime
	def hour
	def windows
	def mapDistribution
	def random = new Random()
	def properties
	
	def loadFromProperties(properties)
	{
		this.properties = properties
		windowSize = Integer.parseInt properties.get(LOAD_WINDOW_SIZE)
		log.info "Window size: " + windowSize
		
		def times = properties.get(LOAD_WINDOW_TIMES).tokenize(';')
		
		assert times.size() == windowSize
		
		def rates = properties.get(LOAD_WINDOW_RATE).tokenize(';')
		
		assert rates.size() == windowSize
		
		log.info times + " - " + rates
		
		// total time in ms
		totalTime = Integer.parseInt properties.get(LOAD_TIME_TOTAL)
		totalTime = totalTime * (60 * 1000)
		log.info "Total time: " + totalTime + " ms"
		
		windows = new LoadWindowConfig[windowSize]
		
		for (i in  0..windowSize-1) {
			int startTime = Integer.parseInt times[i].tokenize(':')[0]
			int finishTime = Integer.parseInt times[i].tokenize(':')[1]
			int rate =  Integer.parseInt rates[i]
			windows[i] = new LoadWindowConfig(startTime: startTime, finishTime: finishTime, rate: rate, id: i)
			log.info i + ": " + windows[i]
		}
		
		Calendar currentTime = Calendar.getInstance()
		hour = currentTime.get(Calendar.HOUR_OF_DAY)
		log.info "Current time: " + CalendarUtil.format(currentTime, YYYY_MM_DD_HH_MM_SS) + " - Hour: " + hour		
		
		loadPackageDistributionConfig(properties)
		
	}

	def loadPackageDistributionConfig(properties) {
		
		numPackages = Integer.parseInt properties.get(LOAD_PACKAGE_NUM_PACKAGES)
		log.info "Number of packages: " + numPackages

		def str = properties.get(LOAD_PACKAGE_SELECTION_DISTRIBUTION)
		def distributionPackages = str.tokenize(';')

		assert distributionPackages.size() == numPackages

		mapDistribution = [:]
		for (c in distributionPackages)
		{
			mapDistribution.putAt(Float.parseFloat(c), 0)
		}
		mapDistribution.sort()
		assert mapDistribution.keySet().size() == numPackages
		log.info "Probability distribution map: " + mapDistribution
	}
	
	def getCurrentWindow()
	{
		hour = CalendarUtil.getCurrentHour()
		return getWindow(hour)
	}

	def getWindow(int hour)
	{
		assert hour >=0 && hour < 24 // 24:00 is accepted as 0:00
		def window
		for (i in 0..windowSize-1) {
			window = windows[i]
			if (hour >= window.startTime && hour < window.finishTime) {
				log.debug "Window " + i + " found"
				return window
			}
		}
	}

	def getSelectedPackage(float value)
	{
		int i = 0
		for (c in mapDistribution.keySet())
		{
			def diff = Math.abs(value - c)
			if (value <= c)
			{
				log.debug "Found distribution: " + c
				mapDistribution.putAt(c, mapDistribution.get(c) + 1)
				return i
			}
			i++
		}
		return -1
	}
	
	def choosePackageRandomly()
	{
		float value = random.nextFloat()
		log.info "Selected: " + value + " - " + mapDistribution + " - Total: " + sumMapDistribution
		def selectedPackageId = getSelectedPackage(value)
		assert selectedPackageId >= 0 && selectedPackageId < mapDistribution.keySet().size()		
		return selectedPackageId
	}
	
	public String toString()
	{
		return this.properties
	}
	
	def getSumMapDistribution()
	{
		float sum = 0
		for (c in mapDistribution)
		{
			sum = sum + c.value
		}
		return sum
	}
	
	def getConfigProperty(key)
	{
		return properties.get(key)
	}
}
