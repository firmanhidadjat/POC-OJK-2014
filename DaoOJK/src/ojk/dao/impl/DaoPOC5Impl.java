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

import ojk.app.poc5.POC5;
import ojk.dao.util.DaoUtil;

public class DaoPOC5Impl {
	static Logger log = LoggerUtil.getLog();

	public static int insert(BigDecimal IDTRANSAKSI,
			Timestamp TANGGALTRANSAKSI, BigDecimal ORIGINALAMOUNT,
			BigDecimal APPROVEDAMOUNT, BigDecimal TAXAMOUNT) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"insert into POC5 (IDTRANSAKSI, TANGGALTRANSAKSI, ORIGINALAMOUNT, APPROVEDAMOUNT, TAXAMOUNT) values(?,?,?,?,?)");
		preparedStatement.setBigDecimal(1, IDTRANSAKSI);
		preparedStatement.setTimestamp(2, TANGGALTRANSAKSI);
		preparedStatement.setBigDecimal(3, ORIGINALAMOUNT);
		preparedStatement.setBigDecimal(4, APPROVEDAMOUNT);
		preparedStatement.setBigDecimal(5, TAXAMOUNT);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		// DaoUtil.getKoneksi().close();
		return 0; // 0 = berhasil; 1 = gagal;
	}

	public static List<POC5> read() throws SQLException, Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"select IDTRANSAKSI, TANGGALTRANSAKSI, ORIGINALAMOUNT, APPROVEDAMOUNT, TAXAMOUNT from POC5 order by IDTRANSAKSI desc");
		ResultSet rs = preparedStatement.executeQuery();
		List<POC5> lp = new ArrayList<POC5>();
		while (rs.next()) {
			lp.add(new POC5(rs.getBigDecimal(1), rs.getDate(2), rs
					.getBigDecimal(3), rs.getBigDecimal(4), rs.getBigDecimal(5)));
		}
		rs.close();
		preparedStatement.close();
		// DaoUtil.getKoneksi().close();
		return lp;
	}

	public static int update(BigDecimal IDTRANSAKSI,
			Timestamp TANGGALTRANSAKSI, BigDecimal ORIGINALAMOUNT,
			BigDecimal APPROVEDAMOUNT, BigDecimal TAXAMOUNT) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"update POC5 set TANGGALTRANSAKSI = ?, ORIGINALAMOUNT = ?, APPROVEDAMOUNT = ?, TAXAMOUNT = ? where IDTRANSAKSI= ? ");
		preparedStatement.setTimestamp(1, TANGGALTRANSAKSI);
		preparedStatement.setBigDecimal(2, ORIGINALAMOUNT);
		preparedStatement.setBigDecimal(3, APPROVEDAMOUNT);
		preparedStatement.setBigDecimal(4, TAXAMOUNT);
		preparedStatement.setBigDecimal(5, IDTRANSAKSI);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}

	public static int delete() throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"delete from poc5 ");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}
}
