package ojk.app.poc4esb;

import java.text.SimpleDateFormat;

import id.co.hanoman.LoggerUtil;
import id.co.hanoman.exception.SOAPFaultThrower;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;

@WebService(serviceName = "POC4ServiceESB", targetNamespace = "http://ojk.com/poc4/submit")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class POC4ServiceESB {
	static Logger log = LoggerUtil.getLog();
	@Resource
	private WebServiceContext ctx;

	@WebMethod(operationName = "echo")
	@WebResult(name = "echo")
	public String echo(@WebParam(name = "echoParam") String s) {
		return s;
	}

	@WebMethod(operationName = "submit")
	@WebResult(name = "POC4Result")
	public POC4Result submit(@WebParam(name = "POC4") POC4 o) {
		POC4Result p = new POC4Result();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			// DaoPOC4Impl.insert(o.getIDTransaksi(), new java.sql.Timestamp(o
			// .getTanggalTransaksi().getTime()));
			p.setIDTransaksi(o.getIDTransaksi());
			p.setStatus("S");
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			p.setIDTransaksi(o.getIDTransaksi());
			p.setStatus("F");
		}
		// try {
		// Thread.sleep(5000);
		// } catch (Exception e) {
		// log.error(LoggerUtil.getStackTrace(e));
		// }
		return p;
	}

	@WebMethod(operationName = "submitAsync")
	@WebResult(name = "POC4")
	public void submitAsync(@WebParam(name = "POC4") POC4 o) throws SOAPFaultThrower {
		try {
			// DaoPOC4Impl.update(o.getIDTransaksi(), new java.sql.Timestamp(o
			// .getTanggalTransaksi().getTime()));
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			throw new SOAPFaultThrower(e.getMessage(), e);
		}
	}

	@WebMethod(operationName = "getResult")
	@WebResult(name = "POC4Result")
	public POC4Result getResult(@WebParam(name = "POC4") POC4 o) {
		POC4Result p = new POC4Result();
		try {
			// DaoPOC4Impl.insert(o.getIDTransaksi(), new java.sql.Timestamp(o
			// .getTanggalTransaksi().getTime()));
			p.setIDTransaksi(o.getIDTransaksi());
			p.setStatus("S");
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			p.setIDTransaksi(o.getIDTransaksi());
			p.setStatus("F");
		}

		return p;
	}

}
