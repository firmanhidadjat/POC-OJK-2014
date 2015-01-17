package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import ojk.app.login.Login;
import ojk.app.poc5.POC5;
import ojk.app.poc5.client.POC5Client;
import ojk.dao.impl.DaoPOC5Impl;

@ManagedBean(name = "App4")
@RequestScoped
// @SessionScoped
public class App4 implements Serializable {
	static Logger log = LoggerUtil.getLog();

	private BigDecimal idTransaksi;
	private Date tanggalTransaksi;
	private BigDecimal originalAmount;
	private BigDecimal approvedAmount = new BigDecimal("0");
	private BigDecimal taxAmount = new BigDecimal("0");
	private POC5 selectedPOC;
	private List<POC5> pocs = new ArrayList<POC5>();
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public App4() {
		refresh();
	}

	@ManagedProperty(value = "#{Login}")
	private Login login;

	public void setLogin(Login clogin) {
		this.login = clogin;
	}

	public Login getLogin() {
		return login;
	}

	@PostConstruct
	public void init() {
		selectedPOC = new POC5();
	}

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void echo() {
		clearMessage();
		POC5Client p = new POC5Client();
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

	public void submit() {
		clearMessage();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			POC5Client p = new POC5Client();
//			p.sendData(login.getUsernameCache(), login.getPasswordCache(),
			p.sendData("Budi", "PasswordBudi",
					"submit", this.idTransaksi.toString(),
					sdf.format(this.tanggalTransaksi),
					this.originalAmount.toString(),
					this.approvedAmount.toString(), this.taxAmount.toString());
			DaoPOC5Impl d = new DaoPOC5Impl();
			d.insert(this.idTransaksi, new java.sql.Timestamp(
					this.tanggalTransaksi.getTime()), this.originalAmount,
					this.approvedAmount, this.taxAmount);
			refresh();
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

	public void refresh() {
		clearMessage();
		idTransaksi = null;
		tanggalTransaksi = null;
		originalAmount = null;

		this.selectedPOC = null;
		try {
			pocs = DaoPOC5Impl.read();
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

	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}

	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public POC5 getSelectedPOC() {
		return selectedPOC;
	}

	public void setSelectedPOC(POC5 selectedPOC) {
		this.selectedPOC = selectedPOC;
	}

	public List<POC5> getPocs() {
		return pocs;
	}

	public void setPocs(List<POC5> pocs) {
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
