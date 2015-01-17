package ojk.app.poc4_1esb.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.co.hanoman.LoggerUtil;

import org.apache.log4j.Logger;

public class RunPOC4_1ESBClient {
	static Logger log = LoggerUtil.getLog();

	public static void main(String[] ad) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		for (int i = 0; i < 1; i++) {
			POC4_1ESBClient d = new POC4_1ESBClient();

//			if (d.getResult("submit", String.valueOf(i), sdf.format(new Date()), "Bandung") != null) {
//				System.out.println("submit Kirim data sakses");
//			} else {
//				System.out.println("submit Kirim data gagal");
//			}
			if (d.sendData("submitAsync", String.valueOf(i + 1),
					sdf.format(new Date()), "Bandung") == 0) {
				System.out.println("submitAsync Kirim data sakses");
			} else {
				System.out.println("submitAsync Kirim data gagal");
			}
			
//			Thread.sleep(6000);
//			if (d.getResult("getResult", String.valueOf(i + 1),
//					sdf.format(new Date()), "Bandung") != null) {
//				System.out.println("getResult Kirim data sakses");
//			} else {
//				System.out.println("getResult Kirim data gagal");
//			}
		}
	}
}
