package ojk.app.poc3;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

import ojk.dao.impl.DaoPOC3Impl;

@WebService(serviceName = "POC3Service", targetNamespace = "http://ojk.com/poc3/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC3Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC3")
	public void submit(@WebParam(name = "POC3") POC3 o) throws SOAPFaultThrower {
		try {
			DaoPOC3Impl.insert(o.getIDTransaksi(), o.getFullName(),
					o.getKeterangan(), String.valueOf(o.getStatus()),
					o.getNilaiTransaksi(), o.getNilaiDisetujui(), o.getPin());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}
}
