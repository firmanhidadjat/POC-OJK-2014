package ojk.dao.impl;

import id.co.hanoman.LoggerUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ojk.app.poc4.POC4;
import ojk.dao.util.DaoUtil;

public class DaoPOC4Impl {
	static Logger log = LoggerUtil.getLog();

	public static int insert(BigDecimal IDTRANSAKSI, Timestamp TANGGALTRANSAKSI)
			throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"insert into POC4 (IDTRANSAKSI, TANGGALTRANSAKSI) values(?,?)");
		preparedStatement.setBigDecimal(1, IDTRANSAKSI);
		preparedStatement.setTimestamp(2, TANGGALTRANSAKSI);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		// DaoUtil.getKoneksi().close();
		return 0; // 0 = berhasil; 1 = gagal;
	}

	public static List<POC4> read() throws SQLException, Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"select IDTRANSAKSI, TANGGALTRANSAKSI from POC4 order by IDTRANSAKSI desc");
		ResultSet rs = preparedStatement.executeQuery();
		List<POC4> lp = new ArrayList<POC4>();
		while (rs.next()) {
			lp.add(new POC4(rs.getBigDecimal(1), rs.getDate(2)));
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
				"update POC4 set TANGGALTRANSAKSI = ? where IDTRANSAKSI= ? ");
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
				"delete from poc4 ");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}
}
