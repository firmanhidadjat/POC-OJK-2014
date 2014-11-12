package ojk.app.poc5;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

import ojk.app.poc5.client.POC5Client;
import ojk.dao.impl.DaoPOC5Impl;

@WebService(serviceName = "POC5Service", targetNamespace = "http://ojk.com/poc5/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC5Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC5")
	public void submit(@WebParam(name = "POC5") POC5 o) throws SOAPFaultThrower {
		try {
			DaoPOC5Impl.insert(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()), o.getOriginalAmount(), o
					.getApprovedAmount(), o.getTaxAmount());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			POC5Client p = new POC5Client();
			p.sendData("submit", o.getIDTransaksi().toString(), sdf.format(o
					.getTanggalTransaksi()), o.getOriginalAmount().toString(),
					o.getApprovedAmount().toString(), o.getTaxAmount()
							.toString());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

	@WebMethod(operationName = "update")
	@WebResult(name = "POC5")
	public void update(@WebParam(name = "POC5") POC5 o) throws SOAPFaultThrower {
		try {
			DaoPOC5Impl.update(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()), o.getOriginalAmount(), o
					.getApprovedAmount(), o.getTaxAmount());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}
}
