import java.util.concurrent.TimeUnit



class TimeUtil {

	public static final int TIME_HOUR_MS = 1000 * 60 * 60
	public static final int TIME_SECONDS_MS = 1000
	
	
	def static convert(long value, TimeUnit unit)
	{
		TimeUnit.MILLISECONDS
		switch (unit)
		{
			case TimeUnit.MILLISECONDS:
				return value
			break
			case TimeUnit.SECONDS:
				return value/1000
			break
			case TimeUnit.MINUTES:
				return value/1000/60
			break
			case TimeUnit.HOURS:
				return value/1000/60/24
			break
		}

	}
	
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
