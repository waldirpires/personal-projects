package com.ericsson.cms.orng.test.util

import java.util.concurrent.TimeUnit

import org.apache.log4j.Logger

class TimeUtils {
	
	private static final Logger log = Logger.getLogger(TimeUtils.class)

	static def formatTime(long millis)
	{
		def hours = TimeUnit.MILLISECONDS.toHours(millis)
		def minutes = TimeUnit.MILLISECONDS.toMinutes(millis) -
			TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))
		def seconds = TimeUnit.MILLISECONDS.toSeconds(millis) -
			TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
		def m = millis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)) 
		def str = String.format("%02d:%02d:%02d:%04d", hours, minutes, seconds, m)
		return str
	}
	
	def sleep(int time)
	{
		log.info "Sleeping for $time ms" 
		int interval = 500
		for (int i = 0; i < time/interval; i++)
		{
			print '. '
			Thread.sleep(interval)
		}
		println ''
	}
	
	def calculatePercentage(long value, long total)
	{
		return SystemUtil.roundNum(value/(double)total * 100)
	}
}
