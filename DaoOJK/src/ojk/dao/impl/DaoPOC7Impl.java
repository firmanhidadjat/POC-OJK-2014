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

import ojk.app.poc7.POC7;
import ojk.dao.util.DaoUtil;

public class DaoPOC7Impl {
	static Logger log = LoggerUtil.getLog();

	public static int insert(BigDecimal IDTRANSAKSI,
			Timestamp TANGGALTRANSAKSI, BigDecimal ORIGINALAMOUNT,
			BigDecimal TAXAMOUNT) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"insert into POC7 (IDTRANSAKSI, TANGGALTRANSAKSI, ORIGINALAMOUNT, TAXAMOUNT) values(?,?,?,?)");
		preparedStatement.setBigDecimal(1, IDTRANSAKSI);
		preparedStatement.setTimestamp(2, TANGGALTRANSAKSI);
		preparedStatement.setBigDecimal(3, ORIGINALAMOUNT);
		preparedStatement.setBigDecimal(4, TAXAMOUNT);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		// DaoUtil.getKoneksi().close();
		return 0; // 0 = berhasil; 1 = gagal;
	}

	public static List<POC7> read() throws SQLException, Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"select IDTRANSAKSI, TANGGALTRANSAKSI, ORIGINALAMOUNT, TAXAMOUNT from POC7 order by IDTRANSAKSI desc");
		ResultSet rs = preparedStatement.executeQuery();
		List<POC7> lp = new ArrayList<POC7>();
		while (rs.next()) {
			lp.add(new POC7(rs.getBigDecimal(1), rs.getDate(2), rs
					.getBigDecimal(3), rs.getBigDecimal(4)));
		}
		rs.close();
		preparedStatement.close();
		// DaoUtil.getKoneksi().close();
		return lp;
	}

	public static int update(BigDecimal IDTRANSAKSI,
			Timestamp TANGGALTRANSAKSI, BigDecimal ORIGINALAMOUNT,
			BigDecimal TAXAMOUNT) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"update POC7 set TANGGALTRANSAKSI = ?, ORIGINALAMOUNT = ?, TAXAMOUNT = ? where IDTRANSAKSI= ? ");
		preparedStatement.setTimestamp(1, TANGGALTRANSAKSI);
		preparedStatement.setBigDecimal(2, ORIGINALAMOUNT);
		preparedStatement.setBigDecimal(3, TAXAMOUNT);
		preparedStatement.setBigDecimal(4, IDTRANSAKSI);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}

	public static int delete() throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"delete from poc7 ");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}
}
