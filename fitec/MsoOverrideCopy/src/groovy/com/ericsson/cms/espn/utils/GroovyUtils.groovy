package com.ericsson.cms.espn.utils

class GroovyUtils {

	def static printClassPath(classLoader) {
		println "$classLoader"
		classLoader.getURLs().each {url->
		   println "- ${url.toString()}"
		}
		if (classLoader.parent) {
		   printClassPath(classLoader.parent)
		}
	  }
}
