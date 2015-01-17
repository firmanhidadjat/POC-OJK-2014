package ojk.app.poc1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import ojk.app.poc1.client.POC1Client;
import ojk.dao.impl.DaoPOC1Impl;

@WebService(serviceName = "POC1Service", targetNamespace = "http://ojk.com/poc1/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
// @HandlerChain(file = "../../../handler-chain.xml")
public class POC1Service {
	static Logger log = LoggerUtil.getLog();
	@Resource
	private WebServiceContext ctx;

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC1")
	public void submit(@WebParam(name = "POC1") POC1 o) throws Exception {

		// if (checkUserPassword()) {
		try {
			DaoPOC1Impl.insert(o.getIDTransaksi(), o.getFullName(),
					o.getKeterangan(), String.valueOf(o.getStatus()),
					o.getNilaiTransaksi(), o.getNilaiDisetujui(),
					o.getPin());
//			POC1Client p = new POC1Client();
//			p.sendData("submit", o.getIDTransaksi().toString(),
//					o.getFullName(), o.getKeterangan(), null, o
//							.getNilaiTransaksi().toString(), "0", o.getPin());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
		// } else {
		// log.error("User/password salah");
		// throw new UserPasswdFault("User/Password mismatch",
		// "User/password salah");
		// }
	}

	@WebMethod(operationName = "update")
	@WebResult(name = "POC1")
	public void update(@WebParam(name = "POC1") POC1 o) throws SOAPFaultThrower {
		try {
			DaoPOC1Impl.update(o.getIDTransaksi(), o.getFullName(),
					o.getKeterangan(), String.valueOf(o.getStatus()),
					o.getNilaiTransaksi(), o.getNilaiDisetujui(),
					o.getPin());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

	private boolean checkUserPassword() throws Exception {

		Map<String, Object> map = ctx.getMessageContext();
		// Bila hendak menampilkan semua isi map dari messagecontext
		// eksekusi looping di bawah ini
		// for (Object obj : map.entrySet()) {
		// System.out.println("YYY : " + obj);
		// }

		SOAPMessage soapMessage = null;
		MessageFactory factory = MessageFactory.newInstance();
		for (Iterator<String> iterator = map.keySet().iterator(); iterator
				.hasNext();) {
			String kunci = (String) iterator.next();
			if (kunci.equals("jaxws.message.accessor")) {

				Object nilai = map.get(kunci);

				soapMessage = factory.createMessage(new MimeHeaders(),
						new ByteArrayInputStream(String.valueOf(nilai)
								.getBytes(Charset.forName("UTF-8"))));
			}
		}
		SOAPHeader soapHeader = soapMessage.getSOAPPart().getEnvelope()
				.getHeader();
		Node userNameElement = soapHeader
				.getElementsByTagNameNS(
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
						"Username").item(0);
		Node passwdElement = soapHeader
				.getElementsByTagNameNS(
						"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
						"Password").item(0);
		if (passwdElement == null || userNameElement == null) {
			return false;
		} else {
			log.debug("User name : " + userNameElement.getTextContent());
			log.debug("Password  : " + passwdElement.getTextContent());

			if (!(userNameElement.getTextContent().equals("Budi") && passwdElement
					.getTextContent().equals("PasswordBudi"))) {
				log.debug("Username/password salah!!!");
				return false;
			}
		}
		return true;
	}
}
