package ojk.app.poc2.client;

import java.io.ByteArrayOutputStream;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultExceptionHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import ojk.app.client.ClientWSUtil;
import ojk.app.poc2.POC2;

public class POC2Client {
	static Logger log = LoggerUtil.getLog();
	// static final String endpoint = "http://hostdb/App2/POC2Service";
	static final String endpoint = "http://hostdb/ESB/POC2Service";
	// static final String endpoint = "http://hostapp1:9080/App2/POC2Service";
	static final String nameSpace = "http://ojk.com/poc2/submit";

	public static String echo(String userName, String passwordStr)
			throws Exception {
		String operationName = "echo";
		SOAPEnvelope soapEnvelope = ClientWSUtil.getSoapEnvelope();
		soapEnvelope.getHeader().removeContents();
		// =============== Tambahkan security header =========================
		SOAPHeader header = soapEnvelope.getHeader();
		SOAPElement securityElement = header
				.addChildElement(
						"Security",
						"wsse",
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

		SOAPElement usernameTokenElement = securityElement.addChildElement(
				"UsernameToken", "wsse");
		SOAPElement usernameElement = usernameTokenElement.addChildElement(
				"Username", "wsse");
		usernameElement
				.setAttribute(
						"xmlns:wsu",
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
		usernameElement.addTextNode(userName);
		SOAPElement password = usernameTokenElement.addChildElement("Password",
				"wsse");
		password.setAttribute(
				"Type",
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		password.addTextNode(passwordStr);
		// ====================================================

		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("echoParam")
				.addTextNode("cobaKoneksi");
		ClientWSUtil.getSoapMessage().saveChanges();
		ClientWSUtil.getSoapMessage().writeTo(System.out);

		SOAPMessage soapResponse = ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint);
		Node dataBody = soapResponse.getSOAPPart().getEnvelope().getBody()
				.getElementsByTagName("echo").item(0);
		if (dataBody == null) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			soapResponse.writeTo(output);
			String strMsg = new String(output.toByteArray());
			if (strMsg.toLowerCase().contains("fault")) {
				log.error("Terjadi kesalahan pada pengiriman data");
				throw new SOAPFaultExceptionHandler(strMsg);
			}
			return null;
		} else {
			log.debug("databody content = " + dataBody.getTextContent());
			return dataBody.getTextContent();
		}
	}

	public int sendData(String username, String passwordStr, String operation, String TransactionID,
			String FirstName, String LastName, String Notes, String Status,
			String TransactionValue, String ApprovedAmount, String Pin)
			throws Exception {

		String operationName = operation.toLowerCase();

		SOAPEnvelope soapEnvelope = ClientWSUtil.getSoapEnvelope();
		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("POC2");
		element0.addChildElement("TransactionID").addTextNode(TransactionID);
		element0.addChildElement("FirstName").addTextNode(FirstName);
		element0.addChildElement("LastName").addTextNode(LastName);
		element0.addChildElement("Notes").addTextNode(Notes);
		element0.addChildElement("Status").addTextNode(Status);
		element0.addChildElement("TransactionValue").addTextNode(
				TransactionValue);
		element0.addChildElement("ApprovedAmount").addTextNode(ApprovedAmount);
		element0.addChildElement("Pin").addTextNode(Pin);
		// =============== Tambahkan security header =========================
		SOAPHeader header = soapEnvelope.getHeader();
		SOAPElement securityElement = header
				.addChildElement(
						"Security",
						"wsse",
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");

		SOAPElement usernameTokenElement = securityElement.addChildElement(
				"UsernameToken", "wsse");
		SOAPElement usernameElement = usernameTokenElement.addChildElement(
				"Username", "wsse");
		usernameElement
				.setAttribute(
						"xmlns:wsu",
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
		usernameElement.addTextNode(username);
		SOAPElement password = usernameTokenElement.addChildElement("Password",
				"wsse");
		password.setAttribute(
				"Type",
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		password.addTextNode(passwordStr);
		// ====================================================

		ClientWSUtil.getSoapMessage().saveChanges();
		// ClientWSUtil.getSoapMessage().writeTo(System.out);

	
		// if(ClientWSUtil.getSoapConnection()!=null){
		// log.debug("uyeeeee1111111111111");
		// }else{
		// log.debug("uyeeeee22222222222");
		// }
		// if(ClientWSUtil.getSoapConnection().call(null, endpoint)!=null){
		// log.debug("uyeeeee33333333333");
		// }else{
		// log.debug("uyeeeee44444444444");
		// }

//		log.debug("++++++++++++++++++ "
//				+ ClientWSUtil.getSoapMessage().toString());
//		log.debug("++++++++++++++++++ "
//				+ ClientWSUtil.getSoapMessage().getContentDescription());

		getObj(ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint));

		return 0;

	}

	public static POC2 getObj(SOAPMessage soapResponse) throws Exception {
		log.debug("CCCCCCCCCCCCCCCCC");
		Node dataBody = soapResponse.getSOAPPart().getEnvelope().getBody()
				.getElementsByTagName("POC2").item(0);
		log.debug("DDDDD");
		if (dataBody == null) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			soapResponse.writeTo(output);
			String strMsg = new String(output.toByteArray());
			if (strMsg.toLowerCase().contains("fault")) {
				log.error("Terjadi kesalahan pada pengiriman data");
				throw new SOAPFaultExceptionHandler(strMsg);
			}
			return null;
		} else {
			JAXBContext jc = JAXBContext.newInstance(POC2.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<POC2> je = unmarshaller.unmarshal(dataBody, POC2.class);
			return je.getValue();
		}
	}
}
