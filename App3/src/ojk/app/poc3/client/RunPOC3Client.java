package ojk.app.poc3.client;

public class RunPOC3Client {
	public static void main(String[] ad) throws Exception {
		// BBSprng bbs = new BBSprng();

		for (int i = 0; i < 10; i++) {
			POC3Client d = new POC3Client();
			if (d.sendData("submit", String.valueOf(i), "Keterangan", "1",
					"234820", "23940", "234") == 0) {
				System.out.println("Kirim data sakses");
			} else {
				System.out.println("Kirim data gagal");
			}
			if (d.sendData("update", String.valueOf(i), "Keterangan", "1",
					"234820", "23940", "234") == 0) {
				System.out.println("Update data sakses");
			} else {
				System.out.println("Update data gagal");
			}
		}

	}
}
