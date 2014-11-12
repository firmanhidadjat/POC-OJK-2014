package ojk.app.poc1.client;

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
import ojk.app.poc1.POC1;

public class POC1Client {
	static Logger log = LoggerUtil.getLog();
	// static final String endpointSubmit =
	// "http://localhost:9080/App1/POC1Service";
	static final String endpoint = "http://hostdb/App1/POC1Service";
	static final String nameSpace = "http://ojk.com/poc1/submit";

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

	public static String echo() throws Exception {
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
		usernameElement.addTextNode("Budi");
		SOAPElement password = usernameTokenElement.addChildElement("Password",
				"wsse");
		password.setAttribute(
				"Type",
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		password.addTextNode("PasswordBudi");
		// ====================================================

		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("echoParam")
				.addTextNode("cobaKoneksi");
		ClientWSUtil.getSoapMessage().saveChanges();
		// ClientWSUtil.getSoapMessage().writeTo(System.out);

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

	public static int sendData(String operation, String IDTransaksi,
			String FullName, String Keterangan, String Status,
			String NilaiTransaksi, String NilaiDisetujui, String Pin)
			throws Exception {
		String operationName = operation.toLowerCase();

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

		// usernameElement.addAttribute(
		// new QName("xmlns:wsu"),
		// "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

		usernameElement
				.setAttribute(
						"xmlns:wsu",
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

		usernameElement.addTextNode("Budi");

		SOAPElement password = usernameTokenElement.addChildElement("Password",
				"wsse");
		password.setAttribute(
				"Type",
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		password.addTextNode("PasswordBudi");
		// ====================================================

		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("POC1");
		element0.addChildElement("IDTransaksi").addTextNode(IDTransaksi);
		element0.addChildElement("FullName").addTextNode(FullName);
		element0.addChildElement("Keterangan").addTextNode(Keterangan);
		element0.addChildElement("Status").addTextNode(Status);
		element0.addChildElement("NilaiTransaksi").addTextNode(NilaiTransaksi);
		element0.addChildElement("NilaiDisetujui").addTextNode(NilaiDisetujui);
		element0.addChildElement("Pin").addTextNode(Pin);

		ClientWSUtil.getSoapMessage().saveChanges();
		// ClientWSUtil.getSoapMessage().writeTo(System.out);
		getObj(ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint));

		return 0;
	}

	public static POC1 getObj(SOAPMessage soapResponse) throws Exception {
		Node dataBody = soapResponse.getSOAPPart().getEnvelope().getBody()
				.getElementsByTagName("POC1").item(0);
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
			JAXBContext jc = JAXBContext.newInstance(POC1.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<POC1> je = unmarshaller.unmarshal(dataBody, POC1.class);
			return je.getValue();
		}

	}
}
