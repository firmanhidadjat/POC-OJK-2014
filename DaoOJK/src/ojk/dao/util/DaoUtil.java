package ojk.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoUtil {
	private static final String url = "jdbc:oracle:thin:@hostdb:1521:orcl";
	private static final String username = "aaasss";
	private static final String passwd = "aaasss";

	private static Connection koneksi;

	public static Connection getKoneksi() throws Exception  {
		if (koneksi == null) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			koneksi = DriverManager.getConnection(url, username, passwd);
		}
		return koneksi;
	}
}
