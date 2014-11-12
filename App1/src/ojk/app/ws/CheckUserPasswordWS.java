package ojk.app.ws;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultExceptionHandler;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CheckUserPasswordWS implements SOAPHandler<SOAPMessageContext> {
	static Logger log = LoggerUtil.getLog();

	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
//		log.debug("Server : handleMessage()......");
//		Boolean isRequest = (Boolean) context
//				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
//		// Method di atas akan menghasilkan FALSE bila message yang diterima
//		// adalah inbound message dan akan menghasilkan TRUE bila message yang
//		// diterima adalah outbound message
//
//		if (!isRequest) { // !isRequest --> artinya hanya mengolah inbound
//							// message
//			try {
//				SOAPMessage soapMessage = context.getMessage();
//				SOAPHeader soapHeader = soapMessage.getSOAPPart().getEnvelope()
//						.getHeader();
//
//				Node userNameElement = soapHeader
//						.getElementsByTagNameNS(
//								"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
//								"Username").item(0);
//
//				Node passwdElement = soapHeader
//						.getElementsByTagNameNS(
//								"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
//								"Password").item(0);
//				if (passwdElement == null || userNameElement == null) {
//					SOAPFaultExceptionHandler.generateSOAPErrMessage(
//							soapMessage, "No user/password");
//				} else {
//					String userName = userNameElement.getTextContent();
//					String password = passwdElement.getTextContent();
//
//					log.debug("User name : " + userName);
//					log.debug("Password  : " + password);
//					System.out.println("User name : " + userName);
//					System.out.println("Password  : " + password);
//
//					if (!(userName.equals("Budi") && password
//							.equals("PasswordBudi"))) {
//						SOAPFaultExceptionHandler.generateSOAPErrMessage(
//								soapMessage, "Wrong user/password.");
//					}
//
//				}
//			} catch (SOAPException e) {
//				log.error(LoggerUtil.getStackTrace(e));
//			}
//		}

		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
