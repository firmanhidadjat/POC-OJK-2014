package ojk.dao.impl;

public class TestDeleteAllPOCTables {
	public static void main(String[] args) {
		String tabel = "all";

		try {
			if (tabel.equalsIgnoreCase("POC1") || tabel.equalsIgnoreCase("All")) {
				DaoPOC1Impl.delete();
				System.out.print(1);
			}
			if (tabel.equalsIgnoreCase("POC2") || tabel.equalsIgnoreCase("All")) {
				DaoPOC2Impl.delete();
				System.out.print(2);
			}
			if (tabel.equalsIgnoreCase("POC3") || tabel.equalsIgnoreCase("All")) {
				DaoPOC3Impl.delete();
				System.out.print(3);
			}
			if (tabel.equalsIgnoreCase("POC4") || tabel.equalsIgnoreCase("All")) {
				DaoPOC4Impl.delete();
				System.out.print(4);
			}
			if (tabel.equalsIgnoreCase("POC5") || tabel.equalsIgnoreCase("All")) {
				DaoPOC5Impl.delete();
				System.out.print(5);
			}
			if (tabel.equalsIgnoreCase("POC6") || tabel.equalsIgnoreCase("All")) {
				DaoPOC6Impl.delete();
				System.out.print(6);
			}
			if (tabel.equalsIgnoreCase("POC7") || tabel.equalsIgnoreCase("All")) {
				DaoPOC7Impl.delete();
				System.out.print(7);
			}
			if (tabel.equalsIgnoreCase("POC2Ver2")
					|| tabel.equalsIgnoreCase("All")) {
				DaoPOC2Ver2Impl.delete();
				System.out.print(8);
			}
			if (tabel.equalsIgnoreCase("POC4_1")
					|| tabel.equalsIgnoreCase("All")) {
				DaoPOC2Ver2Impl.delete();
				System.out.print(9);
			}
			System.out.println();
			System.out.println("Delete " + tabel + " table(s) Sakses");
		} catch (Exception e) {
			System.out.println("DELETE GAGAL");
		}
	}
}
