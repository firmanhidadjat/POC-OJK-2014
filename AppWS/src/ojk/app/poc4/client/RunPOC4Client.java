package ojk.app.poc4.client;

import id.co.hanoman.LoggerUtil;

import org.apache.log4j.Logger;

public class RunPOC4Client {
	static Logger log = LoggerUtil.getLog();

	public static void main(String[] ad) throws Exception {

		for (int i = 0; i < 10; i++) {
			POC4Client d = new POC4Client();

			if (d.sendData("submit", String.valueOf(i), "2014-09-29") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
			if (d.sendData("update", String.valueOf(i), "2014-09-29") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
		}
	}
}
