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

import ojk.app.poc6.POC6;
import ojk.dao.util.DaoUtil;

public class DaoPOC6Impl {
	static Logger log = LoggerUtil.getLog();

	public static int insert(BigDecimal IDTRANSAKSI,
			Timestamp TANGGALTRANSAKSI, BigDecimal ORIGINALAMOUNT,
			BigDecimal APPROVEDAMOUNT) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"insert into POC6 (IDTRANSAKSI, TANGGALTRANSAKSI, ORIGINALAMOUNT, APPROVEDAMOUNT) values(?,?,?,?)");
		preparedStatement.setBigDecimal(1, IDTRANSAKSI);
		preparedStatement.setTimestamp(2, TANGGALTRANSAKSI);
		preparedStatement.setBigDecimal(3, ORIGINALAMOUNT);
		preparedStatement.setBigDecimal(4, APPROVEDAMOUNT);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		// DaoUtil.getKoneksi().close();
		return 0; // 0 = berhasil; 1 = gagal;
	}

	public static List<POC6> read() throws SQLException, Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"select IDTRANSAKSI, TANGGALTRANSAKSI, ORIGINALAMOUNT, APPROVEDAMOUNT from POC6 order by IDTRANSAKSI desc");
		ResultSet rs = preparedStatement.executeQuery();
		List<POC6> lp = new ArrayList<POC6>();
		while (rs.next()) {
			lp.add(new POC6(rs.getBigDecimal(1), rs.getDate(2), rs
					.getBigDecimal(3), rs.getBigDecimal(4)));
		}
		rs.close();
		preparedStatement.close();
		// DaoUtil.getKoneksi().close();
		return lp;
	}

	public static int update(BigDecimal IDTRANSAKSI,
			Timestamp TANGGALTRANSAKSI, BigDecimal ORIGINALAMOUNT,
			BigDecimal APPROVEDAMOUNT) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"update POC6 set TANGGALTRANSAKSI = ?, ORIGINALAMOUNT = ?, APPROVEDAMOUNT = ? where IDTRANSAKSI= ? ");
		preparedStatement.setTimestamp(1, TANGGALTRANSAKSI);
		preparedStatement.setBigDecimal(2, ORIGINALAMOUNT);
		preparedStatement.setBigDecimal(3, APPROVEDAMOUNT);
		preparedStatement.setBigDecimal(4, IDTRANSAKSI);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}

	public static int delete() throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"delete from poc6 ");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}
}
