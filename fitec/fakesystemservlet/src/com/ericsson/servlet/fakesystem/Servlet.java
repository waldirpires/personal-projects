package com.ericsson.servlet.fakesystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * @author Walter Coelho
 * 
 * 
 */

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {

	private static final String CDN = "CDN";
	private static final String RTV = "RTV";

	static MessageFactory messageFactory;
	private String systemName;

	public Servlet(String systemName) {
		try {
			messageFactory = MessageFactory.newInstance();
			this.systemName = systemName;

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/plain");
		resp.getWriter().println(
				"Servlet that returns SOAP messages to " + this.systemName
						+ " Adapter. It only accetps POST messages.");

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			// Get all the headers from the HTTP request
			MimeHeaders headers = getHeaders(req);

			// Construct a SOAPMessage from the XML in the request body
			InputStream is = req.getInputStream();
			SOAPMessage soapRequest = messageFactory.createMessage(headers, is);

			System.out.println("Request: " + soapRequest.getSOAPBody());
			
			SOAPMessage soapResponse = null;

			// Handle soapReqest
			if(this.systemName.equals(CDN))
			{
				SOAPHandlerCDN soapHandler = new SOAPHandlerCDN();
				soapResponse = soapHandler
						.handleSOAPRequest(soapRequest);
			} else if(this.systemName.equals(RTV)){
				SOAPHandlerRTV soapHandler = new SOAPHandlerRTV();	
				soapResponse = soapHandler
						.handleSOAPRequest(soapRequest);
			}
					

				

			// Write to HttpServeltResponse
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");

			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);

			System.out.println("Response: " + soapResponse.getSOAPBody());

			os.flush();
		} catch (SOAPException e) {
			throw new IOException("Exception while creating SOAP message.", e);
		}

	}

	@SuppressWarnings("unchecked")
	static MimeHeaders getHeaders(HttpServletRequest req) {
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			while (values.hasMoreTokens()) {
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		return headers;
	}
}