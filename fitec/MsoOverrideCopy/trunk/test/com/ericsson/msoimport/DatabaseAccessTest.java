package com.ericsson.msoimport;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseAccessTest {

	@Test
	public void testConnect() throws SQLException {
		String url = "jdbc:oracle:thin:@10.116.18.204:1521:ttv";
		String username = "root";
		String password = "root1234";

		DatabaseAccess da = new DatabaseAccess();

		Assert.assertTrue(da.connect(url, username, password));
		da.disconnect();
	}
}
