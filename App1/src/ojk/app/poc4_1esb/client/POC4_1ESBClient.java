package ojk.app.poc4_1esb.client;

import java.io.ByteArrayOutputStream;
import java.util.Date;

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
import ojk.app.poc4_1esb.POC4_1Result;
import ojk.app.poc4esb.POC4Result;

public class POC4_1ESBClient {
	static Logger log = LoggerUtil.getLog();
	// static final String endpoint = "http://hostdb/AppWS1/POC4ServiceESB";
	static final String endpoint = "http://hostdb/ESB/POC4ServiceESB";
	static final String nameSpace = "http://ojk.com/poc4/submit/2";

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
			String TanggalTransaksi, String Lokasi) throws Exception {
		String operationName = operation;

		// try {
		SOAPEnvelope soapEnvelope = ClientWSUtil.getSoapEnvelope();
		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("POC4");
		element0.addChildElement("IDTransaksi").addTextNode(IDTransaksi);
		element0.addChildElement("TanggalTransaksi").addTextNode(
				TanggalTransaksi);
		element0.addChildElement("Lokasi").addTextNode(Lokasi);
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

		ClientWSUtil.getSoapMessage().saveChanges();
		// ClientWSUtil.getSoapMessage().writeTo(System.out);
		getObj(ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint));
		return 0;
		// } catch (Exception e) {
		// log.error(LoggerUtil.getStackTrace(e));
		// return 1; // return gagal
		// }
	}

	public static POC4_1Result getResult(String username, String passwordStr,
			String operation, String IDTransaksi, String TanggalTransaksi,
			String Lokasi) throws Exception {

		String operationName = operation;
		// try {
		SOAPEnvelope soapEnvelope = ClientWSUtil.getSoapEnvelope();
		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("POC4");
		element0.addChildElement("IDTransaksi").addTextNode(IDTransaksi);
		element0.addChildElement("TanggalTransaksi").addTextNode(
				TanggalTransaksi);
		element0.addChildElement("Lokasi").addTextNode(Lokasi);

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
		POC4_1Result p = getObj(ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint));
		return p;
		// } catch (Exception e) {
		// log.error(LoggerUtil.getStackTrace(e));
		// return null; // return gagal
		// }
	}

	public static POC4_1Result getObj(SOAPMessage soapResponse)
			throws Exception {
		Node dataBody = soapResponse.getSOAPPart().getEnvelope().getBody()
				.getElementsByTagName("POC4").item(0);
		if (dataBody == null) {
			dataBody = soapResponse.getSOAPPart().getEnvelope().getBody()
					.getElementsByTagName("POC4Result").item(0);
		}

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
			JAXBContext jc = JAXBContext.newInstance(POC4_1Result.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<POC4_1Result> je = unmarshaller.unmarshal(dataBody,
					POC4_1Result.class);
			return je.getValue();
		}
	}
}
