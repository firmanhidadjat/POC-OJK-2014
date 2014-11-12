package ojk.app.poc7.client;

import id.co.hanoman.LoggerUtil;

import org.apache.log4j.Logger;

public class RunPOC7Client {
	static Logger log = LoggerUtil.getLog();
	public static void main(String[] ad) throws Exception {
		for (int i = 0; i < 1; i++) {
			POC7Client d = new POC7Client();

			if (d.sendData("submit", String.valueOf(i), "2013-05-02", "2057890", "32890") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
			if (d.sendData("update", String.valueOf(i), "2013-05-02", "2057890", "32890") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
		}

	}
}
