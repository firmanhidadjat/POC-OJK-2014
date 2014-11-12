package ojk.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class TestInsertUpdatePOC1 {
	public static void main(String[] ag) throws Exception {
		int dodol = 1;
		for (int i = 0; i < dodol; i++) {
			if (DaoPOC1Impl.insert(new BigDecimal(i), "Budi Anduk",
					"Keterangan cuy", null, new BigDecimal("1235235"), null,
					"999") == 1) {
				System.out.println("Insert DB Gagaaaaaal");
			} else {
				System.out.println("insert Berhasil berhasil hore hore");
			}
		}

		for (int i = 0; i < dodol; i++) {
			if (DaoPOC1Impl.update(new BigDecimal(i), "Budi Anduk",
					"Keterangan cuy", "S", new BigDecimal("111111"),
					new BigDecimal("222222"), "3333") == 1) {
				System.out.println("Update DB Gagaaaaaal");
			} else {
				System.out.println("Update Berhasil berhasil hore hore");
			}
		}

		// ================================================================

		for (int i = 0; i < 1; i++) {

			if (DaoPOC2Impl.insert(new BigDecimal(i), "Budi", "Anduk",
					"NOTESSSS", "s", new BigDecimal("1111111111111"),
					new BigDecimal("222222"), "3333333") == 1) {
				System.out.println("Insert DB Gagaaaaaal");
			} else {
				System.out.println("insert Berhasil berhasil hore hore");
			}
		}
		for (int i = 0; i < 1; i++) {
			if (DaoPOC2Impl.update(new BigDecimal(i), "Budi", "Anduk",
					"NOTESSSS", "s", new BigDecimal("1111111111111"),
					new BigDecimal("222222"), "3333333") == 1) {
				System.out.println("Update DB Gagaaaaaal");
			} else {
				System.out.println("Update Berhasil berhasil hore hore");
			}
		}

		// ================================================================

		for (int i = 0; i < 1; i++) {

			if (DaoPOC3Impl.insert(new BigDecimal(i), "BUDI anduk",
					"KETERANGAN " + i, "S", new BigDecimal("1111111111111"),
					new BigDecimal("2222222"), "333333") == 1) {
				System.out.println("Insert DB Gagaaaaaal");
			} else {
				System.out.println("insert Berhasil berhasil hore hore");
			}
		}

		for (int i = 0; i < 1; i++) {
			if (DaoPOC3Impl.update(new BigDecimal(i), "Budiupdate Anduk",
					"KETERANGAN " + i, "S", new BigDecimal("1111111111111"),
					new BigDecimal("2222222"), "333333") == 1) {
				System.out.println("Update DB Gagaaaaaal");
			} else {
				System.out.println("Update Berhasil berhasil hore hore");
			}
		}

		// ================================================================

		for (int i = 0; i < 1; i++) {

			if (DaoPOC4Impl.insert(new BigDecimal(i),
					new Timestamp(new Date().getTime())) == 1) {
				System.out.println("Insert DB Gagaaaaaal");
			} else {
				System.out.println("insert Berhasil berhasil hore hore");
			}
		}

		for (int i = 0; i < 1; i++) {
			if (DaoPOC4Impl.update(new BigDecimal(i),
					new Timestamp(new Date().getTime())) == 1) {
				System.out.println("Update DB Gagaaaaaal");
			} else {
				System.out.println("Update Berhasil berhasil hore hore");
			}
		}

		// ================================================================

		for (int i = 0; i < 1; i++) {
			if (DaoPOC5Impl.insert(new BigDecimal(i),
					new Timestamp(new Date().getTime()), new BigDecimal(
							"1111111111111"), new BigDecimal("2222222"),
					new BigDecimal("333333")) == 1) {
				System.out.println("Insert DB Gagaaaaaal");
			} else {
				System.out.println("insert Berhasil berhasil hore hore");
			}
		}

		for (int i = 0; i < 1; i++) {
			if (DaoPOC5Impl.update(new BigDecimal(i),
					new Timestamp(new Date().getTime()), new BigDecimal(
							"1111111111111"), new BigDecimal("2222222"),
					new BigDecimal("333333")) == 1) {
				System.out.println("Update DB Gagaaaaaal");
			} else {
				System.out.println("Update Berhasil berhasil hore hore");
			}
		}

		// ================================================================

		for (int i = 0; i < 1; i++) {
			if (DaoPOC6Impl.insert(new BigDecimal(i),
					new Timestamp(new Date().getTime()), new BigDecimal(
							"1111111111111"), new BigDecimal("2222222")) == 1) {
				System.out.println("Insert DB Gagaaaaaal");
			} else {
				System.out.println("insert Berhasil berhasil hore hore");
			}
		}

		for (int i = 0; i < 1; i++) {
			if (DaoPOC6Impl.update(new BigDecimal(i),
					new Timestamp(new Date().getTime()), new BigDecimal(
							"1111111111111"), new BigDecimal("2222222")) == 1) {
				System.out.println("Update DB Gagaaaaaal");
			} else {
				System.out.println("Update Berhasil berhasil hore hore");
			}
		}

		// ================================================================

		for (int i = 0; i < 1; i++) {
			if (DaoPOC7Impl.insert(new BigDecimal(i),
					new Timestamp(new Date().getTime()), new BigDecimal(
							"1111111111111"), new BigDecimal("2222222")) == 1) {
				System.out.println("Insert DB Gagaaaaaal");
			} else {
				System.out.println("insert Berhasil berhasil hore hore");
			}
		}

		for (int i = 0; i < 1; i++) {
			if (DaoPOC7Impl.update(new BigDecimal(i),
					new Timestamp(new Date().getTime()), new BigDecimal(
							"1111111111111"), new BigDecimal("2222222")) == 1) {
				System.out.println("Update DB Gagaaaaaal");
			} else {
				System.out.println("Update Berhasil berhasil hore hore");
			}
		}

		// ==================================================================
		try {
			DaoPOC1Impl.delete();
			DaoPOC2Impl.delete();
			DaoPOC3Impl.delete();
			DaoPOC4Impl.delete();
			DaoPOC5Impl.delete();
			DaoPOC6Impl.delete();
			DaoPOC7Impl.delete();
			System.out.println("DELETE SAKSES");
		} catch (Exception e) {
			System.out.println("DELETE GAGAL");
		}

	}
}
