package ojk.dao.impl;

import id.co.hanoman.LoggerUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ojk.app.poc2.POC2;
import ojk.dao.util.DaoUtil;

public class DaoPOC2Impl {
	static Logger log = LoggerUtil.getLog();

	public static int insert(BigDecimal TRANSACTIONID, String FIRSTNAME,
			String LASTNAME, String NOTES, String STATUS,
			BigDecimal TRANSACTIONVALUE, BigDecimal APPROVEDAMOUNT,
			String PIN) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"insert into POC2 (TRANSACTIONID, FIRSTNAME, LASTNAME, NOTES, STATUS, TRANSACTIONVALUE, APPROVEDAMOUNT, PIN) values(?,?,?,?,?,?,?,?)");
		preparedStatement.setBigDecimal(1, TRANSACTIONID);
		preparedStatement.setString(2, FIRSTNAME);
		preparedStatement.setString(3, LASTNAME);
		preparedStatement.setString(4, NOTES);
		preparedStatement.setString(5, STATUS);
		preparedStatement.setBigDecimal(6, TRANSACTIONVALUE);
		preparedStatement.setBigDecimal(7, APPROVEDAMOUNT);
		preparedStatement.setString(8, PIN);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		// DaoUtil.getKoneksi().close();
		return 0; // 0 = berhasil; 1 = gagal;
	}

	public static List<POC2> read() throws SQLException, Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"select TRANSACTIONID, FIRSTNAME, LASTNAME, NOTES, STATUS, TRANSACTIONVALUE, APPROVEDAMOUNT, PIN from POC2 order by TRANSACTIONID desc");
		ResultSet rs = preparedStatement.executeQuery();
		List<POC2> lp = new ArrayList<POC2>();
		while (rs.next()) {
			lp.add(new POC2(rs.getBigDecimal(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getString(5), rs
					.getBigDecimal(6), rs.getBigDecimal(7), rs.getString(8)));
		}
		rs.close();
		preparedStatement.close();
		// DaoUtil.getKoneksi().close();
		return lp;
	}

	public static int update(BigDecimal TRANSACTIONID, String FIRSTNAME,
			String LASTNAME, String NOTES, String STATUS,
			BigDecimal TRANSACTIONVALUE, BigDecimal APPROVEDAMOUNT,
			String PIN) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"update POC2 set FIRSTNAME = ?, LASTNAME = ?, NOTES = ?, STATUS = ?, TRANSACTIONVALUE = ?, APPROVEDAMOUNT = ?, PIN = ? where TRANSACTIONID = ?");
		preparedStatement.setString(1, FIRSTNAME);
		preparedStatement.setString(2, LASTNAME);
		preparedStatement.setString(3, NOTES);
		preparedStatement.setString(4, STATUS);
		preparedStatement.setBigDecimal(5, TRANSACTIONVALUE);
		preparedStatement.setBigDecimal(6, APPROVEDAMOUNT);
		preparedStatement.setString(7, PIN);
		preparedStatement.setBigDecimal(8, TRANSACTIONID);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}

	public static int delete() throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"delete from poc2 ");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}
}
