package ojk.app.poc6.client;

import id.co.hanoman.LoggerUtil;

import org.apache.log4j.Logger;

public class RunPOC6Client {
	static Logger log = LoggerUtil.getLog();

	public static void main(String[] ad) throws Exception {
		String username = "Budi";
		String password = "PasswordBudi";
		for (int i = 0; i < 10; i++) {
			POC6Client d = new POC6Client();
			// p = d.sendData(String.valueOf(i), "2018-04-05", "23523", "2525");
			if (d.sendData(username, password, "submit", String.valueOf(i),
					"2018-04-05", "23523", "2525") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
			if (d.sendData(username, password, "update", String.valueOf(i),
					"2018-04-05", "23523", "2525") == 0) {
				System.out.println("Update data sakses");
			} else {
				System.out.println("Update data gagal");
			}
		}

	}
}
