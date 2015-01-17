package ojk.app.poc3.client;

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
import ojk.app.poc3.POC3;

public class POC3Client {
	static Logger log = LoggerUtil.getLog();
	// static final String endpoint = "http://localhost:9080/App3/POC3Service";
//	static final String endpoint = "http://hostdb/App3/POC3Service";
	static final String endpoint = "http://hostdb/ESB/POC3Service";
	static final String nameSpace = "http://ojk.com/poc3/submit";

	public int sendData(String operation, String IDTransaksi,
			String Keterangan, String Status, String NilaiTransaksi,
			String NilaiDisetujui, String Pin) throws Exception {

		String operationName = operation.toLowerCase();

		SOAPEnvelope soapEnvelope = ClientWSUtil.getSoapEnvelope();
		soapEnvelope.getBody().removeContents();
		soapEnvelope.addNamespaceDeclaration("ns1", nameSpace);
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody
				.addChildElement(operationName, "ns1");
		SOAPElement element0 = soapElement.addChildElement("POC3");
		element0.addChildElement("IDTransaksi").addTextNode(IDTransaksi);
		element0.addChildElement("Keterangan").addTextNode(Keterangan);
		element0.addChildElement("Status").addTextNode(Status);
		element0.addChildElement("NilaiTransaksi").addTextNode(NilaiTransaksi);
		element0.addChildElement("NilaiDisetujui").addTextNode(NilaiDisetujui);
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
		usernameElement.addTextNode("Budi");
		SOAPElement password = usernameTokenElement.addChildElement("Password",
				"wsse");
		password.setAttribute(
				"Type",
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		password.addTextNode("PasswordBudi");
		// ====================================================

		ClientWSUtil.getSoapMessage().saveChanges();
		getObj(ClientWSUtil.getSoapConnection().call(
				ClientWSUtil.getSoapMessage(), endpoint));
		return 0;
	}

	public static POC3 getObj(SOAPMessage soapResponse) throws Exception {
		Node dataBody = soapResponse.getSOAPPart().getEnvelope().getBody()
				.getElementsByTagName("POC3").item(0);
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
			JAXBContext jc = JAXBContext.newInstance(POC3.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<POC3> je = unmarshaller.unmarshal(dataBody, POC3.class);
			return je.getValue();
		}
	}
}
