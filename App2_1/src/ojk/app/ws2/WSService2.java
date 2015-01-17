package ojk.app.ws2;

import id.co.hanoman.LoggerUtil;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

@WebService(serviceName = "WSService2", targetNamespace = "http://ojk.com/WSTest/2014/12/1")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class WSService2 {
	static Logger log = LoggerUtil.getLog();

	@WebMethod(operationName = "statTest")
	@WebResult(name = "status")
	public String statTest(@WebParam(name = "name") String name,
			@WebParam(name = "category") String category,
			@WebParam(name = "amount") BigDecimal amount) {
		String status = "OK";
		return status;
	}
}
