package ojk.dao.impl;

import id.co.hanoman.LoggerUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ojk.app.poc4_1.POC4_1;
import ojk.dao.util.DaoUtil;

import org.apache.log4j.Logger;

public class DaoPOC4_1Impl {
	static Logger log = LoggerUtil.getLog();

	public static int insert(BigDecimal IDTRANSAKSI,
			Timestamp TANGGALTRANSAKSI, String LOKASI) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"insert into POC4_1 (IDTRANSAKSI, TANGGALTRANSAKSI, LOKASI) values(?,?,?)");
		preparedStatement.setBigDecimal(1, IDTRANSAKSI);
		preparedStatement.setTimestamp(2, TANGGALTRANSAKSI);
		preparedStatement.setString(3, LOKASI);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		// DaoUtil.getKoneksi().close();
		return 0; // 0 = berhasil; 1 = gagal;
	}

	public static List<POC4_1> read() throws SQLException, Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"select IDTRANSAKSI, TANGGALTRANSAKSI, LOKASI from POC4_1 order by IDTRANSAKSI desc");
		ResultSet rs = preparedStatement.executeQuery();
		List<POC4_1> lp = new ArrayList<POC4_1>();
		while (rs.next()) {
			lp.add(new POC4_1(rs.getBigDecimal(1), rs.getDate(2), rs
					.getString(3)));
		}
		rs.close();
		preparedStatement.close();
		// DaoUtil.getKoneksi().close();
		return lp;
	}

	public static int update(BigDecimal IDTRANSAKSI, Timestamp TANGGALTRANSAKSI)
			throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"update POC4_1 set TANGGALTRANSAKSI = ? where IDTRANSAKSI= ? ");
		preparedStatement.setTimestamp(1, TANGGALTRANSAKSI);
		preparedStatement.setBigDecimal(2, IDTRANSAKSI);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}

	public static int delete() throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"delete from poc4_1 ");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}
}
