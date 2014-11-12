package ojk.app.client;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class ClientWSUtil {
	private static SOAPEnvelope soapEnvelope;
	private static SOAPMessage soapMessage;
	private static SOAPConnection soapConnection;

	public static SOAPConnection getSoapConnection()
			throws UnsupportedOperationException, SOAPException {
		if (soapConnection == null) {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
		}
		return soapConnection;
	}

	public static SOAPMessage getSoapMessage()
			throws UnsupportedOperationException, SOAPException {
		if (soapMessage == null) {
			MessageFactory messageFactory = MessageFactory.newInstance();
			soapMessage = messageFactory.createMessage();
		}
		return soapMessage;
	}

	public static SOAPEnvelope getSoapEnvelope()
			throws UnsupportedOperationException, SOAPException {
		if (soapEnvelope == null) {
			SOAPPart soapPart = getSoapMessage().getSOAPPart();
			soapEnvelope = soapPart.getEnvelope();
		}
		return soapEnvelope;
	}

}
