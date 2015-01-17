package ojk.app.poc4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

import ojk.dao.impl.DaoPOC4Impl;

@WebService(serviceName = "POC4Service", targetNamespace = "http://ojk.com/poc4/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC4Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC4Result")
	public POC4Result submit(@WebParam(name = "POC4") POC4 o)
			throws SOAPFaultThrower {
		POC4Result p = new POC4Result();

		try {
			log.debug("POC4SERVICE.SUBMIT");
			DaoPOC4Impl.insert(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()));
			p.setIDTransaksi(o.getIDTransaksi());
			if (o.getIDTransaksi().intValue() % 2 == 0) {
				p.setStatus("S");
			} else {
				p.setStatus("F");
			}
			Thread.sleep(5000);
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
		return p;
	}
}
