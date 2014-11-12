package ojk.app.poc1;

import id.co.hanoman.LoggerUtil;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;
import ojk.dao.impl.DaoPOC1Impl;

@WebService(serviceName = "POC1Service", targetNamespace = "http://ojk.com/poc1/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@HandlerChain(file = "handler-chain.xml")
public class POC1Service {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC1")
	public void submit(@WebParam(name = "POC1") POC1 o) {
		try {
//			DaoPOC1Impl.insert(o.getIDTransaksi(), o.getFullName(),
//					o.getKeterangan(), String.valueOf(o.getStatus()),
//					o.getNilaiTransaksi(), o.getNilaiDisetujui(), o.getPin());
//			POC1Client p = new POC1Client();
//			p.sendData("submit", o.getIDTransaksi().toString(),
//					o.getFullName(), o.getKeterangan(), null, o
//							.getNilaiTransaksi().toString(), "0", o.getPin()
//							.toString());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
		}
	}

	@WebMethod(operationName = "update")
	@WebResult(name = "POC1")
	public void update(@WebParam(name = "POC1") POC1 o) {
		try {
//			DaoPOC1Impl.update(o.getIDTransaksi(), o.getFullName(),
//					o.getKeterangan(), String.valueOf(o.getStatus()),
//					o.getNilaiTransaksi(), o.getNilaiDisetujui(), o.getPin());
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
		}
	}

}
