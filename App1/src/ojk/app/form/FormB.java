package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import ojk.app.poc1.client.POC1Client;
import ojk.app.poc4esb.POC4Result;
import ojk.app.poc4esb.client.POC4ESBClient;

@ManagedBean(name = "formB")
@ViewScoped
public class FormB implements Serializable {
	static Logger log = LoggerUtil.getLog();
	private BigDecimal idTransaksi;
	private Date tanggalTransaksi;
	private String status;

	private String idTransaksiTbl;
	private String tanggalTransaksiTbl;
	private String statusTbl;
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public FormB() {
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
			if (!p.echo().equals("cobaKoneksi")) {
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
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public void kirimData(ActionEvent actionEvent) {
		clearMessage();
		idTransaksiTbl = "";
		tanggalTransaksiTbl = "";
		statusTbl = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			POC4ESBClient.sendData("submitAsync", idTransaksi.toString(),
					sdf.format(tanggalTransaksi));

		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
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
			POC4Result d = POC4ESBClient.getResult("getResult",
					idTransaksi.toString(), sdf.format(tanggalTransaksi));

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			statusTbl = d.getStatus();
			idTransaksiTbl = d.getIDTransaksi().toString();
			tanggalTransaksiTbl = sdf2.format(tanggalTransaksi);
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
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

}
