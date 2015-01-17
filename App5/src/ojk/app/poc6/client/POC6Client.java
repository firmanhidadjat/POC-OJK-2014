package ojk.app.poc6.client;

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
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import ojk.app.client.ClientWSUtil;
import ojk.app.poc6.POC6;

public class POC6Client {
	static Logger log = LoggerUtil.getLog();
	// static final String endpoint = "http://localhost:9080/App5/POC6Service";
	static final String endpoint = "http://hostdb/abc/App5/POC6Service";
	// static final String endpoint = "http://hostdb/ESB/POC6Service";
	static final String nameSpace = "http://ojk.com/poc6/submit";

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

	public int sendData(String username, String passwordStr, String operation,
			String IDTransaksi, String TanggalTransaksi, String OriginalAmount,
			String ApprovedAmount) throws Exception {

		String operationName = operation.toLowerCase();

		SOAPEnvelope soapEnvelope = ClientWSUtil.getSoapEnvelope();
		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("POC6");
		element0.addChildElement("IDTransaksi").addTextNode(IDTransaksi);
		element0.addChildElement("TanggalTransaksi").addTextNode(
				TanggalTransaksi);
		element0.addChildElement("OriginalAmount").addTextNode(OriginalAmount);
		element0.addChildElement("ApprovedAmount").addTextNode(ApprovedAmount);
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
		getObj(ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint));
		return 0;

	}

	public static POC6 getObj(SOAPMessage soapResponse) throws Exception {

		Node dataBody = soapResponse.getSOAPPart().getEnvelope().getBody()
				.getElementsByTagName("POC6").item(0);
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
			JAXBContext jc = JAXBContext.newInstance(POC6.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<POC6> je = unmarshaller.unmarshal(dataBody, POC6.class);
			return je.getValue();
		}

	}
}
