package ojk.app.poc1;

import id.co.hanoman.LoggerUtil;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

public class RunPOC1Service {
	static Logger log = LoggerUtil.getLog();

	public static void main(String[] sbargrgf) {
		String endpoint = "http://localhost:7001/WS";
		System.out.println("Menjalankan WS...");
		Endpoint.publish(endpoint, new POC1Service());
		System.out.println("WS jalan... endpoint: " + endpoint);
	}
}
