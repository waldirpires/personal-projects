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

/**
 * @author Walter Coelho
 * 
 * 
 */

public class SOAPHandlerRTV {

	private static final String GET_AVAILABLE_TIME_ZONE_LIST = "getAvailableTimeZoneList";

	private MessageFactory messageFactory;

	public SOAPHandlerRTV() throws SOAPException {
		this.messageFactory = MessageFactory.newInstance();

	}

	public SOAPMessage handleSOAPRequest(SOAPMessage request)
			throws SOAPException {
		SOAPBody soapBody = request.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();

		SOAPMessage sm = null;

		String namespace = "";
		String elementName = "";

		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof SOAPElement) {
				SOAPElement soapElement = (SOAPElement) next;
				elementName = soapElement.getLocalName();
				String prefix = soapElement.getPrefix();
				namespace = soapElement.getNamespaceURI(prefix);
				break;
			}

		}

		sm = createMessageForRTV(namespace, elementName);

		return sm;

	}

	private SOAPMessage createMessageForRTV(String namespace, String elementName)
			throws SOAPException {

		SOAPMessage sm = messageFactory.createMessage();

		SOAPHeader sh = sm.getSOAPHeader();
		SOAPBody sb = sm.getSOAPBody();
		sh.detachNode();

		QName bodyName = new QName(namespace, elementName + "Response", "ns");
		SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);

		QName returnName = new QName(namespace, "return", "ns");

		SOAPElement returnChild = bodyElement.addChildElement(returnName);

		if (elementName.equals(GET_AVAILABLE_TIME_ZONE_LIST)) {

			QName qn = new QName(
					"http://entities.common.interfaces.rightv.orca.com/xsd",
					"timeZoneId");
			SOAPElement quotation = returnChild.addChildElement(qn);

			quotation.addTextNode("Europe/Paris");
		}

		return sm;
	}

}
