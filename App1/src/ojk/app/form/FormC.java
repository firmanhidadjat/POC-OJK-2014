package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import ojk.app.login.Login;
import ojk.app.poc1.client.POC1Client;
import ojk.app.poc4_1esb.POC4_1Result;
import ojk.app.poc4_1esb.client.POC4_1ESBClient;

@ManagedBean(name = "formC")
@RequestScoped
public class FormC implements Serializable {
	static Logger log = LoggerUtil.getLog();
	private BigDecimal idTransaksi;
	private Date tanggalTransaksi;
	private String status;
	private POC4_1Result pr;
	private String idTransaksiTbl;
	private String tanggalTransaksiTbl;
	private String statusTbl;
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;
	private String lokasi;
	private String lokasiTbl;

	@ManagedProperty(value = "#{Login}")
	private Login login;

	public void setLogin(Login clogin) {
		this.login = clogin;
	}

	public Login getLogin() {
		return login;
	}

	public FormC() {
		clearMessage();
	}

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void echo() {
		clearMessage();
		POC1Client p = new POC1Client();
		try {
			if (!p.echo(login.getUsernameCache(), login.getPasswordCache())
					.equals("cobaKoneksi")) {
				this.strMessage = "Koneksi error";
				this.strMessageLong = "Koneksi error";
				this.kondisiWarn = true;
			} else {
				this.strMessage = "Koneksi sukses";
				this.strMessageLong = "Koneksi sukses";
				this.kondisiWarn = true;
			}

		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			if (e.getMessage().contains("Unauthorized address")) {
				this.strMessage = "Invalid user name or password";
			} else if (e.getMessage().contains("Forbidden IP Address")) {
				this.strMessage = "Forbidden IP Address";
			} else {
				this.strMessage = e.getMessage();
				// throw new Exception(e);
			}
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public void kirimData(ActionEvent actionEvent) {
		clearMessage();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			// POC4_1Result d = POC4_1ESBClient.getResult("submit",
			// idTransaksi.toString(), sdf.format(tanggalTransaksi));

			pr = POC4_1ESBClient.getResult(login.getUsernameCache(),
					login.getPasswordCache(), "submit", idTransaksi.toString(),
					sdf.format(tanggalTransaksi), lokasi.toString());

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			idTransaksiTbl = pr.getIDTransaksi().toString();
			tanggalTransaksiTbl = sdf2.format(tanggalTransaksi);
			statusTbl = pr.getStatus();
			lokasiTbl = pr.getLokasi();

		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			if (e.getMessage().contains("Unauthorized address")) {
				this.strMessage = "Invalid user name or password";
			} else if (e.getMessage().contains("Forbidden IP Address")) {
				this.strMessage = "Forbidden IP Address";
			} else {
				this.strMessage = e.getMessage();
				// throw new Exception(e);
			}
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public void getData() {
		clearMessage();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			// POC4_1ESBClient p = new POC4_1ESBClient();
			// POC4_1Result d = p.getResult("getResult", idTransaksi,
			// sdf.format(tanggalTransaksi));
			POC4_1Result d = POC4_1ESBClient.getResult(
					login.getUsernameCache(), login.getPasswordCache(),
					"getResult", idTransaksi.toString(),
					sdf.format(tanggalTransaksi), lokasi.toString());

			// pr = POC4_1ESBClient.getResult("getResult",
			// idTransaksi.toString(),
			// sdf.format(tanggalTransaksi));

			this.idTransaksi = new BigDecimal(d.getIDTransaksi().toString());
			// this.tanggalTransaksi
			this.status = d.getStatus();
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			if (e.getMessage().contains("Unauthorized address")) {
				this.strMessage = "Invalid user name or password";
			} else if (e.getMessage().contains("Forbidden IP Address")) {
				this.strMessage = "Forbidden IP Address";
			} else {
				this.strMessage = e.getMessage();
				// throw new Exception(e);
			}
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public BigDecimal getIdTransaksi() {
		return idTransaksi;
	}

	public void setIdTransaksi(BigDecimal idTransaksi) {
		this.idTransaksi = idTransaksi;
	}

	public Date getTanggalTransaksi() {
		return tanggalTransaksi;
	}

	public void setTanggalTransaksi(Date tanggalTransaksi) {
		this.tanggalTransaksi = tanggalTransaksi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdTransaksiTbl() {
		return idTransaksiTbl;
	}

	public void setIdTransaksiTbl(String idTransaksiTbl) {
		this.idTransaksiTbl = idTransaksiTbl;
	}

	public String getTanggalTransaksiTbl() {
		return tanggalTransaksiTbl;
	}

	public void setTanggalTransaksiTbl(String tanggalTransaksiTbl) {
		this.tanggalTransaksiTbl = tanggalTransaksiTbl;
	}

	public String getStatusTbl() {
		return statusTbl;
	}

	public void setStatusTbl(String statusTbl) {
		this.statusTbl = statusTbl;
	}

	public String getStrMessageLong() {
		return strMessageLong;
	}

	public void setStrMessageLong(String strMessageLong) {
		this.strMessageLong = strMessageLong;
	}

	public String getStrMessage() {
		return strMessage;
	}

	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}

	public boolean isKondisiWarn() {
		return kondisiWarn;
	}

	public void setKondisiWarn(boolean kondisiWarn) {
		this.kondisiWarn = kondisiWarn;
	}

	public POC4_1Result getPr() {
		return pr;
	}

	public void setPr(POC4_1Result pr) {
		this.pr = pr;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getLokasiTbl() {
		return lokasiTbl;
	}

	public void setLokasiTbl(String lokasiTbl) {
		this.lokasiTbl = lokasiTbl;
	}

}
