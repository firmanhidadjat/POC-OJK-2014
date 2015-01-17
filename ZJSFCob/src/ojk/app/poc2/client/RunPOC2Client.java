package ojk.app.poc2.client;

import id.co.hanoman.LoggerUtil;

import org.apache.log4j.Logger;

public class RunPOC2Client {
	static Logger log = LoggerUtil.getLog();

	public static void main(String[] ad) throws Exception {

		for (int i = 0; i < 10; i++) {
			POC2Client d = new POC2Client();
			if (d.sendData("submit", String.valueOf(i), "FIRSTNAME",
					"LastName", "Notes", "1", "23523", "45784", "23578") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
			if (d.sendData("update", String.valueOf(i), "FIRSTNAME",
					"LastName", "Notes", "1", "23523", "45784", "23578") == 0) {
				System.out.println("Update data sakses");
			} else {
				System.out.println("Update data gagal");
			}
		}

	}
}
