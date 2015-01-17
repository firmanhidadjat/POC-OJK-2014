package ojk.app.poc7;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

import ojk.app.poc7.client.POC7Client;
import ojk.dao.impl.DaoPOC7Impl;

@WebService(serviceName = "POC7Service", targetNamespace = "http://ojk.com/poc7/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC7Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC7")
	public void submit(@WebParam(name = "POC7") POC7 o) throws SOAPFaultThrower {
		try {
			DaoPOC7Impl.insert(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()), o.getOriginalAmount(), o
					.getTaxAmount());

		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

	@WebMethod(operationName = "update")
	@WebResult(name = "POC7")
	public void update(@WebParam(name = "POC7") POC7 o) throws SOAPFaultThrower {
		try {
			DaoPOC7Impl.update(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()), o.getOriginalAmount(), o
					.getTaxAmount());
			POC7Client p = new POC7Client();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			p.sendData("StringUsername", "StringPassword", "update", o
					.getIDTransaksi().toString(), sdf.format(o
					.getTanggalTransaksi()), o.getOriginalAmount().toString(),
					o.getTaxAmount().toString());

		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}
}
