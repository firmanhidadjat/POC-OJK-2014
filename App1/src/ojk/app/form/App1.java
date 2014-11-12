package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import ojk.app.poc1.POC1;
import ojk.app.poc1.client.POC1Client;
import ojk.dao.impl.DaoPOC1Impl;

@ManagedBean(name = "App1")
@RequestScoped
public class App1 implements Serializable {

	static Logger log = LoggerUtil.getLog();
	private BigDecimal idTransaksi;
	private String fullName;
	private String keterangan;
	private String status;
	private BigDecimal nilaiTransaksi;
	private BigDecimal nilaiDisetujui;
	private BigDecimal pin;
	private List<POC1> pocs;
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public App1() {
		refresh();
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

	public void submit() {
		clearMessage();
		// POC1Client p = new POC1Client();
		try {
			POC1Client.sendData("submit", idTransaksi.toString(), fullName,
					keterangan, "", nilaiTransaksi.toString(), "0",
					pin.toString());
			DaoPOC1Impl.insert(idTransaksi, fullName, keterangan, "",
					nilaiTransaksi, new BigDecimal("0"), pin.toString());
			idTransaksi = null;
			fullName = null;
			keterangan = null;
			status = null;
			nilaiTransaksi = null;
			pin = null;

			DaoPOC1Impl d = new DaoPOC1Impl();
			pocs = d.read();
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public void refresh() {
		clearMessage();
		try {
			idTransaksi = null;
			fullName = null;
			keterangan = null;
			status = null;
			nilaiTransaksi = null;
			pin = null;

			DaoPOC1Impl d = new DaoPOC1Impl();
			pocs = d.read();
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getNilaiTransaksi() {
		return nilaiTransaksi;
	}

	public void setNilaiTransaksi(BigDecimal nilaiTransaksi) {
		this.nilaiTransaksi = nilaiTransaksi;
	}

	public BigDecimal getNilaiDisetujui() {
		return nilaiDisetujui;
	}

	public void setNilaiDisetujui(BigDecimal nilaiDisetujui) {
		this.nilaiDisetujui = nilaiDisetujui;
	}

	public BigDecimal getPin() {
		return pin;
	}

	public void setPin(BigDecimal pin) {
		this.pin = pin;
	}

	public List<POC1> getPocs() {
		return pocs;
	}

	public void setPocs(List<POC1> pocs) {
		this.pocs = pocs;
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
