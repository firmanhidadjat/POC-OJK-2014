package ojk.esb.ws.client;

import org.apache.log4j.Logger;

import id.co.hanoman.LoggerUtil;

public class RunPOC4ResultClient {
	static Logger log = LoggerUtil.getLog();
	public static void main(String[] dslfm) throws Exception {

		for (int i = 0; i < 1; i++) {
			POC4ResultClient d = new POC4ResultClient();

			if (d.sendData("123124", "2014-05-07") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
		}
	}
}
