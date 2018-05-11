package com.ericsson.cms.espn.dao;

import static org.junit.Assert.*

import org.junit.After
import org.junit.Assert;
import org.junit.Before
import org.junit.Test

class SitesDaoTestIntegration {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		SitesDao dao = new SitesDao()
		
		Assert.assertNotNull(dao.getSqlObj())
	}

}
