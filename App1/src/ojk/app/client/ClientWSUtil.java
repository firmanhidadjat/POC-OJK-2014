package ojk.app.client;

import id.co.hanoman.LoggerUtil;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.log4j.Logger;

import com.ibm.ws.soap.resources.soap;

public class ClientWSUtil {
	static Logger log = LoggerUtil.getLog();
	private static SOAPEnvelope soapEnvelope;
	private static SOAPMessage soapMessage;
	private static SOAPConnection soapConnection;

	public static SOAPConnection getSoapConnection() {
		try {
			if (soapConnection == null) {
				log.debug("wsclient getkoneksi null");
				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
						.newInstance();
				soapConnection = soapConnectionFactory.createConnection();
			}
			return soapConnection;
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			return null; // return gagal
		}
	}

	public static SOAPMessage getSoapMessage() {
		try {
			if (soapMessage == null) {
				MessageFactory messageFactory = MessageFactory.newInstance();
				soapMessage = messageFactory.createMessage();
			}
			return soapMessage;
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			return null; // return gagal
		}
	}

	public static SOAPEnvelope getSoapEnvelope() {
		try {
			if (soapEnvelope == null) {
				SOAPPart soapPart = getSoapMessage().getSOAPPart();
				soapEnvelope = soapPart.getEnvelope();
			}
			return soapEnvelope;
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			return null; // return gagal
		}
	}
}
