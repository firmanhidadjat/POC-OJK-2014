package ojk.app.poc1.client;

import id.co.hanoman.LoggerUtil;

import org.apache.log4j.Logger;

public class RunPOC1Client {
	static Logger log = LoggerUtil.getLog();

	public static void main(String[] ad) throws Exception {
		for (int i = 0; i < 10; i++) {
			POC1Client d = new POC1Client();
			if (d.sendData("Budi", "PasswordBudi", "submit", String.valueOf(i), "Budi Anduk", "keterangan", "1",
					"234324", "984358934", "23472374") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
//			if (d.sendData("update", String.valueOf(i), "Budi Anduk", "keterangan", "1",
//					"234324", "984358934", "23472374") == 0) {
//				System.out.println("Update data sakses");
//			} else {
//				System.out.println("Update data gagal");
//			}
		}
	}
}
