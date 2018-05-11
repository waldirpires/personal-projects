package com.ericsson.cms.orng.test.util

import java.text.SimpleDateFormat

class CalendarUtil {

	static def String format(Calendar date, String format)
	{
		return new SimpleDateFormat(format).format(date.getTime());
	}
	
	static def getCurrentHour()
	{
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
	}
}
