

package msooverridecopy

import groovy.sql.Sql

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

import org.junit.Assert
import org.junit.Test

import com.ericsson.cms.espn.domain.SiteGroupCopy

class SitesServiceTest {

	@Test
	public void testGetSql()
	{
		def builder     = DocumentBuilderFactory.newInstance().newDocumentBuilder()
		def inputStream = new ByteArrayInputStream(new File("/opt/tandbergtv/cms/jboss/server/standard/deploy/neptune-ds.xml").bytes)
		def records     = builder.parse(inputStream).documentElement
		//def sql = new Sql(dataSource)
		def xpath = XPathFactory.newInstance().newXPath()		
		def nodes = xpath.evaluate( '//local-tx-datasource', records, XPathConstants.NODESET )
		def sql
		nodes.each{
		  def url = xpath.evaluate( 'connection-url', it )
		  def driverClass = xpath.evaluate( 'driver-class', it )
		  def username = xpath.evaluate( 'user-name', it )
		  def password = xpath.evaluate( 'password', it )
		  log.info url + "\t" + driverClass + "\t" + username + "\t" + password
		  sql = Sql.newInstance(url, username,
				  password, driverClass)
		}
		
		Assert.assertNotNull(sql)
	}
	

	@Test
	public void testGetSql2()
	{
		def sitesService = new SitesService()
		def sql = sitesService.getSql()
		Assert.assertNotNull(sql)
	}

		
	@Test
	public void testExecuteSiteGroupCopy()
	{
		def srcSiteId = '17'
		def destSiteId = '18'
		
		def sitesService = new SitesService()
		def siteGroupCopyInstance = new SiteGroupCopy(srcSiteId: srcSiteId, destSiteId: destSiteId)
		sitesService.executeSiteGroupCopy(siteGroupCopyInstance)
		
	}

}
