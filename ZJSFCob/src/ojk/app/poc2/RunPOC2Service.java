package ojk.app.poc2;

import javax.xml.ws.Endpoint;



public class RunPOC2Service {
	public static void main(String[] sbargrgf) {

		String endpoint = "http://localhost:7002/WS";

		System.out.println("Menjalankan WS...");
		Endpoint.publish(endpoint, new POC2Service());
		System.out.println("WS jalan... endpoint: "+endpoint);
	}
}
