package ojk.app.poc5.client;

import ojk.app.poc5.POC5;

public class RunPOC5Client {
	public static void main(String[] ad) throws Exception {
		String username = "Budi";
		String password = "PasswordBudi";
		for (int i = 0; i < 10; i++) {
			POC5Client d = new POC5Client();
			if (d.sendData(username, password, "Submit", String.valueOf(i),
					"2019-04-12", "2523", "28959", "2835") == 0) {
				System.out.println("KIrim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
			if (d.sendData(username, password, "update", String.valueOf(i),
					"2019-04-12", "2523", "28959", "2835") == 0) {
				System.out.println("Update data sakses");
			} else {
				System.out.println("Update data gagal");
			}
		}

	}
}
