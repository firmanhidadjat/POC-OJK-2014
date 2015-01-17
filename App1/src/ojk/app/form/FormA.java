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
import ojk.app.poc4esb.POC4Result;
import ojk.app.poc4esb.client.POC4ESBClient;

@ManagedBean(name = "formA")
@RequestScoped
public class FormA implements Serializable {
	static Logger log = LoggerUtil.getLog();
	private BigDecimal idTransaksi;
	private Date tanggalTransaksi;
	private String status;
	private POC4Result pr;
	private String idTransaksiTbl;
	private String tanggalTransaksiTbl;
	private String statusTbl;
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	@ManagedProperty(value = "#{Login}")
	private Login login;

	public void setLogin(Login clogin) {
		this.login = clogin;
	}

	public Login getLogin() {
		return login;
	}

	public FormA() {
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
			// POC4Result d = POC4ESBClient.getResult("submit",
			// idTransaksi.toString(), sdf.format(tanggalTransaksi));

			pr = POC4ESBClient.getResult(login.getUsernameCache(),
					login.getPasswordCache(), "submit", idTransaksi.toString(),
					sdf.format(tanggalTransaksi));

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			idTransaksiTbl = pr.getIDTransaksi().toString();
			tanggalTransaksiTbl = sdf2.format(tanggalTransaksi);
			statusTbl = pr.getStatus();

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
			// POC4ESBClient p = new POC4ESBClient();
			// POC4Result d = p.getResult("getResult", idTransaksi,
			// sdf.format(tanggalTransaksi));
			POC4Result d = POC4ESBClient.getResult(login.getUsernameCache(),
					login.getPasswordCache(), "getResult",
					idTransaksi.toString(), sdf.format(tanggalTransaksi));

			// pr = POC4ESBClient.getResult("getResult", idTransaksi.toString(),
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

	public POC4Result getPr() {
		return pr;
	}

	public void setPr(POC4Result pr) {
		this.pr = pr;
	}

}
