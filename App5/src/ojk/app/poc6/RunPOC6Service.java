package ojk.app.poc6;

import id.co.hanoman.LoggerUtil;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;



public class RunPOC6Service {
	static Logger log = LoggerUtil.getLog();
	public static void main(String[] sbargrgf) {

		String endpoint = "http://localhost:7006/WS";

		System.out.println("Menjalankan WS...");
		Endpoint.publish(endpoint, new POC6Service());
		System.out.println("WS jalan... endpoint: "+endpoint);
	}
}
