package ojk.app.poc4_1;

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

import ojk.dao.impl.DaoPOC4_1Impl;

@WebService(serviceName = "POC4Service", targetNamespace = "http://ojk.com/poc4/submit/2")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC4_1Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC4_1Result")
	public POC4_1Result submit(@WebParam(name = "POC4") POC4_1 o)
			throws SOAPFaultThrower {
		POC4_1Result p = new POC4_1Result();

		try {
			log.debug("POC4_1SERVICE.SUBMIT");
			
			DaoPOC4_1Impl.insert(o.getIDTransaksi(), new java.sql.Timestamp(o
					.getTanggalTransaksi().getTime()), o.getLokasi());
			p.setIDTransaksi(o.getIDTransaksi());
			p.setLokasi(o.getLokasi());
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
