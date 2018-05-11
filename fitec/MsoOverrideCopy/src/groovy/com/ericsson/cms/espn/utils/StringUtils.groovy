package com.ericsson.cms.espn.utils

class StringUtils {

	def static isEmpty(s)
	{
		return s == null || s.trim().equals('')
	}
}
