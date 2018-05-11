package com.ericsson.msoimport;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class DatabaseAccess {
	private static final Logger logger = Logger.getLogger(DatabaseAccess.class);
	private Connection conn;

	static {
		LogAppender.addAppender(logger);
	}

	public interface ResultSetProcessor {
		public void process(ResultSet rs) throws SQLException;
	}

	/**
	 * Opens the XML file and parses it into a DOM Document.
	 * 
	 * @param filePath
	 *            complete path of the XML file to be parsed.
	 * @return the Document
	 * @throws Exception
	 *             the exception
	 */
	public static Document parseDocumentFromXml(final String filePath) throws Exception {
		try {
			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			return docBuilder.parse(new File(filePath));
		} catch (final Exception e) {
			logger.error("XMLFileParser: exception while parsing file : " + filePath + " " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Evaluate a document based on xpath expression
	 * 
	 * @param xpathExpression
	 *            String containing xpath to be evaluated,
	 * @param doc
	 *            DOM document
	 * @return String with result of evaluation
	 * @throws Exception
	 *             the exception
	 */

	private static String evaluateXPath(String xpath_expression, Document doc) throws XPathExpressionException {

		XPath xpath = XPathFactory.newInstance().newXPath();
		return xpath.evaluate(xpath_expression, doc);
	}

	public void connect(String neptuneXML) throws Exception {
		Document doc = parseDocumentFromXml(neptuneXML);
		// Load the JDBC driver
		String driverName = evaluateXPath("/datasources/local-tx-datasource/driver-class", doc);
		Class.forName(driverName);

		// Create a connection to the database
		String url = evaluateXPath("/datasources/local-tx-datasource/connection-url", doc);
		String username = evaluateXPath("/datasources/local-tx-datasource/user-name", doc);
		String password = evaluateXPath("/datasources/local-tx-datasource/password", doc);
		connect(url, username, password);
		// conn = DriverManager.getConnection(url, username, password);
	}

	public boolean connect(String url, String username, String password) throws SQLException {
		logger.info("Connecting to DB: " + url);
		conn = DriverManager.getConnection(url, username, password);
		return conn != null;
	}

	public void disconnect() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e);
			}
	}

	public void executeQuery(String query, String[] parameters, ResultSetProcessor processor) throws Exception {
		checkConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			for (String parameter : parameters)
				query = query.replaceFirst("\\?", parameter);
			statement = conn.prepareStatement(query);
			rs = statement.executeQuery();
			processor.process(rs);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					logger.warn(e);
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					logger.warn(e);
				}
		}
	}

	private void checkConnection() throws Exception {
		if (conn == null)
			throw new Exception("No database connection");
	}

	public int executeStatement(String query, String[] parameters) throws Exception {
		checkConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			for (String parameter : parameters)
				query = query.replaceFirst("\\?", parameter);
			statement = conn.prepareStatement(query);
			return statement.executeUpdate();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					logger.warn(e);
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					logger.warn(e);
				}
		}
	}
}
