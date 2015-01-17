package ojk.app.login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import id.co.hanoman.LoggerUtil;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import ojk.app.client.ClientWSUtil;

public class LoginClient {
	static Logger log = LoggerUtil.getLog();
	static final String endpoint = "http://hostqm2:7080/ESB/POC1Service";
	static final String nameSpace = "http://ojk.com/poc1/submit";

	public static String login(String username, String passwordStr)
			throws Exception {
		String hasil = "berhasil login";
		String operationName = "echo";

		SOAPEnvelope soapEnvelope = ClientWSUtil.getSoapEnvelope();
		// try {
		soapEnvelope.getHeader().removeContents();
		// =============== Tambahkan security header
		// =========================
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

		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("echoParam")
				.addTextNode("cobaKoneksi");

		ClientWSUtil.getSoapMessage().saveChanges();
		// ClientWSUtil.getSoapMessage().writeTo(System.out);

		// } catch (Exception e) {
		// log.error(LoggerUtil.getStackTrace(e));
		// return "Internal Error";
		// }

		// ClientWSUtil.getSoapConnection().call(
		// ClientWSUtil.getSoapMessage(), endpoint);

		// return "Koneksi Sukses";
		// try {
		String str = "Koneksi Sukses";
		str = getObj(ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint));
		if (str.contains("Forbidden IP Address")) {
			hasil = "Forbidden IP Address";
		} else if (str.contains("Bad response: (401Unauthorized")) {
			hasil = "Invalid username or password";
		}
		return str;
		//
		// log.error("++++++++++++++++++++++++++++++++++++++++++=  " + hasil);
		// return hasil;
		// } catch (Exception e) {
		// String str = LoggerUtil.getStackTrace(e);
		// if (str.contains("Bad response: (401Unauthorized")) {
		// hasil = "Invalid username or password";
		// }
		// log.error("++++++++++++++++++++++++++++++++++++++++++=  " + hasil);
		// return hasil;
		// }
	}

	public static String getObj(SOAPMessage soapResponse) throws Exception{
		String str = "Koneksi Sukses";
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			soapResponse.writeTo(output);
//			soapResponse.writeTo(System.out);
			String strMsg = new String(output.toByteArray());
			if (strMsg.toLowerCase().contains("fault")) {
				Node faultString = soapResponse.getSOAPPart().getEnvelope()
						.getBody().getElementsByTagName("faultstring").item(0);
				str = faultString.getTextContent();
			} else if (strMsg.contains("Bad response: (401Unauthorized")) {
				str = "Invalid username or password";
			} else if (strMsg.contains("Forbidden IP Address")) {
				str = "Forbidden IP Address";
			}
		} catch (Exception e) {
			log.error("<NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
			if (LoggerUtil.getStackTrace(e).contains(
					"Bad response: (401Unauthorized")) {
				str = "Invalid username or password";
			} else if (LoggerUtil.getStackTrace(e).contains(
					"Forbidden IP Address")) {
				str = "Forbidden IP Address";
			}
		}
		return str;
	}

	public static void main(String[] a) throws Exception {
		System.out.println("GGGGGGGGGGGGGGGGGG  "
				+ LoginClient.login("Budi", "PasswordBudi"));
	}
}