package ojk.app.poc2;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

//import ojk.app.poc2.client.POC2Client;
import ojk.dao.impl.DaoPOC2Ver2Impl;

@WebService(serviceName = "POC2Service", targetNamespace = "http://ojk.com/poc2/submit/ver2")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC2Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC2")
	public void submit(@WebParam(name = "POC2") POC2Ver2 o)
			throws SOAPFaultThrower {
		try {
//			DaoPOC2Ver2Impl.insert(o.getTransactionID(), o.getFirstName(),
//					o.getLastName(), o.getNotes(), null,
//					o.getTransactionValue(), null, o.getPin(),
//					o.getOriginalAmount());
			
			DaoPOC2Ver2Impl.insert(o.getTransactionID(), o.getFirstName(),
					o.getLastName(), o.getNotes(), o.getStatus(),
					o.getTransactionValue(), o.getApprovedAmount(), o.getPin(),
					o.getOriginalAmount());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

	@WebMethod(action = "update")
	@WebResult(name = "POC2")
	public void update(@WebParam(name = "POC2") POC2Ver2 o)
			throws SOAPFaultThrower {
		try {
			DaoPOC2Ver2Impl.update(o.getTransactionID(), o.getFirstName(),
					o.getLastName(), o.getNotes(),
					String.valueOf(o.getStatus()), o.getTransactionValue(),
					o.getApprovedAmount(), o.getPin(), o.getOriginalAmount());

//			POC2Client p = new POC2Client();
//
//			p.sendData("update", o.getTransactionID().toString(), o
//					.getFirstName(), o.getLastName(), o.getNotes(), o
//					.getStatus(), o.getTransactionValue().toString(), o
//					.getApprovedAmount().toString(), o.getPin());

		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

}
