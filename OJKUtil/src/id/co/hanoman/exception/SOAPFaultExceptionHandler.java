package id.co.hanoman.exception;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.soap.SOAPFaultException;

public class SOAPFaultExceptionHandler extends Exception {
	public SOAPFaultExceptionHandler(String message) {
		super(message);
	}

	public SOAPFaultExceptionHandler() {

	}

//	public SOAPFaultException generateSOAPErrMessage(SOAPMessage msg,
//			String reason) {
//		try {
//			SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
//			if(soapBody==null){
//				System.out.println("SOAPBODYNULLLLLLLLLLLLLLLLLL");
//			}else{
//				System.out.println("SOAPBODYNOTTTTTTTTTTTNUL");
//			}
//			SOAPFault soapFault = soapBody.addFault();
//			soapFault.setFaultString(reason);
//			return new SOAPFaultException(soapFault);
//		} catch (SOAPException e) {
//			return null;
//		}
//	}
}
