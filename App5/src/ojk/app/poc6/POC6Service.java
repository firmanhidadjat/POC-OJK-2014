package ojk.app.poc6;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

import ojk.app.poc6.client.POC6Client;
import ojk.dao.impl.DaoPOC6Impl;

@WebService(serviceName = "POC6Service", targetNamespace = "http://ojk.com/poc6/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC6Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC6")
	public void submit(@WebParam(name = "POC6") POC6 o) throws SOAPFaultThrower {
		try {
			DaoPOC6Impl.insert(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()), o.getOriginalAmount(), o
					.getApprovedAmount());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

	@WebMethod(operationName = "update")
	@WebResult(name = "POC6")
	public void update(@WebParam(name = "POC6") POC6 o) throws SOAPFaultThrower {
		try {
			DaoPOC6Impl.update(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()), o.getOriginalAmount(), o
					.getApprovedAmount());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			POC6Client p = new POC6Client();
			p.sendData("update", o.getIDTransaksi().toString(), sdf.format(o
					.getTanggalTransaksi()), o.getOriginalAmount().toString(),
					o.getApprovedAmount().toString());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

}
