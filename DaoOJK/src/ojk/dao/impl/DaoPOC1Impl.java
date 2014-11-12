package ojk.dao.impl;

import id.co.hanoman.LoggerUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ojk.app.poc1.POC1;
import ojk.dao.util.DaoUtil;

public class DaoPOC1Impl {
	static Logger log = LoggerUtil.getLog();

	public static int insert(BigDecimal IDTRANSAKSI, String FULLNAME,
			String KETERANGAN, String STATUS, BigDecimal NILAITRANSAKSI,
			BigDecimal NILAIDISETUJUI, String PIN) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"insert into POC1 (IDTRANSAKSI, FULLNAME, KETERANGAN, STATUS, NILAITRANSAKSI, NILAIDISETUJUI, PIN) values(?,?,?,?,?,?,?)");
		preparedStatement.setBigDecimal(1, IDTRANSAKSI);
		preparedStatement.setString(2, FULLNAME);
		preparedStatement.setString(3, KETERANGAN);
		preparedStatement.setString(4, null);
		preparedStatement.setBigDecimal(5, NILAITRANSAKSI);
		preparedStatement.setBigDecimal(6, null);
		preparedStatement.setString(7, PIN);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		// DaoUtil.getKoneksi().close();
		return 0; // 0 = berhasil; 1 = gagal;
	}

	public static List<POC1> read() throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"select IDTRANSAKSI, FULLNAME, KETERANGAN, STATUS, NILAITRANSAKSI, NILAIDISETUJUI, PIN from POC1 order by IDTRANSAKSI desc");
		ResultSet rs = preparedStatement.executeQuery();
		List<POC1> lp = new ArrayList<POC1>();
		while (rs.next()) {
			lp.add(new POC1(rs.getBigDecimal(1), rs.getString(2), rs
					.getString(3), rs.getString(4), rs.getBigDecimal(5), rs
					.getBigDecimal(6), rs.getString(7)));
		}
		rs.close();
		preparedStatement.close();
		// DaoUtil.getKoneksi().close();
		return lp;
	}

	public static int update(BigDecimal IDTRANSAKSI, String FULLNAME,
			String KETERANGAN, String STATUS, BigDecimal NILAITRANSAKSI,
			BigDecimal NILAIDISETUJUI, String PIN) throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil
				.getKoneksi()
				.prepareStatement(
						"update POC1 set STATUS = ?, NILAIDISETUJUI = ? where IDTRANSAKSI= ? ");
		preparedStatement.setString(1, STATUS);
		preparedStatement.setBigDecimal(2, NILAIDISETUJUI);
		preparedStatement.setBigDecimal(3, IDTRANSAKSI);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}

	public static int delete() throws Exception {
		PreparedStatement preparedStatement;
		preparedStatement = DaoUtil.getKoneksi().prepareStatement(
				"delete from poc1 ");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DaoUtil.getKoneksi().commit();
		return 0;
	}

}
