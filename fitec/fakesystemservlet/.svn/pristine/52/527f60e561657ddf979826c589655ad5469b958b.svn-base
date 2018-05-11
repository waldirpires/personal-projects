package com.ericsson.servlet.fakesystem;

import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

/**
 * @author Walter Coelho
 * 
 * 
 */

public class SOAPHandlerCDN {

	private static final String GET_ASSET_DISTRIBUTION_STATUS = "getAssetDistributionStatus";
	private static final String GET_ASSET_SERVICE_STATUS = "getAssetServiceStatus";
	private static final String DELETE_ASSET = "deleteAsset";
	private static final String ADD_VOD_ASSET = "addVODAsset";
	private static final String PLAYABLE = "PLAYABLE";
	private static final String DEPLOYED = "DEPLOYED";
	private static final String DELETED = "DELETED";
	private static final String LOGOUT = "logout";
	private static final String LOGIN = "login";

	private static final Logger logger = Logger.getLogger(SOAPHandlerCDN.class);

	private static String currentDistributionStatus;

	private MessageFactory messageFactory;

	public SOAPHandlerCDN() throws SOAPException {
		this.messageFactory = MessageFactory.newInstance();
	}

	public SOAPMessage handleSOAPRequest(SOAPMessage request)
			throws SOAPException {
		SOAPBody soapBody = request.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();

		SOAPMessage sm = null;

		String namespace = "";
		String elementName = "";
		String userName = null;

		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof SOAPElement) {
				SOAPElement soapElement = (SOAPElement) next;
				elementName = soapElement.getLocalName();
				String prefix = soapElement.getPrefix();
				namespace = soapElement.getNamespaceURI(prefix);

				if (elementName.equals("login")) {
					userName = soapElement.getFirstChild().getTextContent();

				}
				break;
			}

		}


			sm = createMessageForCDN(elementName, namespace, userName);

		return sm;

	}


	private SOAPMessage createMessageForCDN(String elementName,
			String namespace, String userName) throws SOAPException {

		SOAPMessage sm = messageFactory.createMessage();
		SOAPHeader sh = sm.getSOAPHeader();
		SOAPBody sb = sm.getSOAPBody();
		sh.detachNode();

		logger.debug("Element name = " + elementName);

		if (elementName.equals("login") && !userName.equals("iptv")) // Invalid
																		// user
																		// for
																		// heartbeat
		{

			logger.debug("SOAP message for CDN Heartbeat");

			QName bodyName = new QName(
					"http://schemas.xmlsoap.org/soap/envelope/", "Fault",
					"soapenv");

			SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);

			bodyElement.addChildElement("faultcode").addTextNode(
					"soapenv:Server.userException");
			bodyElement
					.addChildElement("faultString")
					.addTextNode(
							"com.bitband.maestro.center.communication.clientcomm.plugin.iptv.LoginException: User does not exist - invalid; nested exception is javax.security.auth.login.LoginException: User does not exist - invalid");

			SOAPElement detailElement = bodyElement.addChildElement("detail");

			detailElement
					.addChildElement(
							"com.bitband.maestro.center.communication.clientcomm.plugin.iptv.LoginException")
					.addChildElement("message")
					.addTextNode(
							"User does not exist - invalid; nested exception is javax.security.auth.login.LoginException: User does not exist - invalid");
			detailElement.addChildElement("hostname", "ns2",
					"http://xml.apache.org/axis/").addTextNode("INCDNMC5");

		} else {

			logger.debug("Response SOAP message for CDN - " + elementName);

			QName bodyName = new QName(namespace, elementName + "Response",
					"ns");
			SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);

			bodyElement.setAttribute("soapenv:encodingStyle",
					"http://schemas.xmlsoap.org/soap/encoding/");

			QName returnName = new QName(elementName + "Return");

			SOAPElement returnChild = bodyElement.addChildElement(returnName);

			if (elementName.equals(LOGOUT) || elementName.equals(LOGIN)) {

				returnChild.addTextNode("2656346408999334912");

			} else if (elementName.equals(ADD_VOD_ASSET)
					|| elementName.equals(DELETE_ASSET)) {

				returnChild.addTextNode("155880");

				if (elementName.equals(ADD_VOD_ASSET)) {
					currentDistributionStatus = DEPLOYED;
				} else {
					currentDistributionStatus = DELETED;
				}

			} else {

				QName multiRef = new QName("multiRef");

				SOAPBodyElement multiRefElement = sb.addBodyElement(multiRef);

				returnChild.setAttribute("href", "#id0");

				multiRefElement.setAttribute("id", "id0");
				multiRefElement.setAttribute("soapenv:encodingStyle",
						"http://schemas.xmlsoap.org/soap/encoding/");

				if (elementName.equals(GET_ASSET_DISTRIBUTION_STATUS)) {

					if (currentDistributionStatus != null)
						multiRefElement.addTextNode(currentDistributionStatus);
					else
						multiRefElement.addTextNode(DEPLOYED);

				} else if (elementName.equals(GET_ASSET_SERVICE_STATUS)) {

					multiRefElement.addChildElement("distributionStatus")
							.setAttribute("href", "#id1");
					multiRefElement.addChildElement("serviceStatus")
							.setAttribute("href", "#id2");

					SOAPBodyElement multiRefElement2 = sb
							.addBodyElement(multiRef);

					multiRefElement2.setAttribute("id", "id2");
					multiRefElement2.setAttribute("soapenv:encodingStyle",
							"http://schemas.xmlsoap.org/soap/encoding/");

					multiRefElement2.addTextNode(PLAYABLE);

					SOAPBodyElement multiRefElement3 = sb
							.addBodyElement(multiRef);

					multiRefElement3.setAttribute("id", "id1");
					multiRefElement3.setAttribute("soapenv:encodingStyle",
							"http://schemas.xmlsoap.org/soap/encoding/");

					multiRefElement3.addTextNode(DEPLOYED);

				}

			}
		}

		return sm;
	}


}
