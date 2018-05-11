import groovy.sql.Sql

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

import com.ericsson.cms.espn.utils.GroovyUtils;

class BootStrap {

	def init = { servletContext ->

//		log.info "Running boot strap . . ."
//		def builder     = DocumentBuilderFactory.newInstance().newDocumentBuilder()
//		def inputStream = new ByteArrayInputStream(new File("/opt/tandbergtv/cms/jboss/server/standard/deploy/neptune-ds.xml").bytes)
//		def records     = builder.parse(inputStream).documentElement
//		def xpath = XPathFactory.newInstance().newXPath()
//		def nodes = xpath.evaluate( '//local-tx-datasource', records, XPathConstants.NODESET )
//		def sql
//		nodes.each{
//			def url = xpath.evaluate( 'connection-url', it )
//			def driverClass = xpath.evaluate( 'driver-class', it )
//			def username = xpath.evaluate( 'user-name', it )
//			def password = xpath.evaluate( 'password', it )
//			log.info url + "\t" + driverClass + "\t" + username + "\t" + password
//			
//			sql = Sql.newInstance(url, username,
//					password, driverClass)
//		}
//		assert sql != null
		GroovyUtils.printClassPath this.class.classLoader

	}

		def destroy = {
		}
	}
