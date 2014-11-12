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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import ojk.app.poc7.POC7;
import ojk.app.poc7.client.POC7Client;
import ojk.dao.impl.DaoPOC6Impl;
import ojk.dao.impl.DaoPOC7Impl;

@ManagedBean(name = "App6")
// @RequestScoped
@ViewScoped
public class App6 implements Serializable {
	static Logger log = LoggerUtil.getLog();
	private POC7 selectedPOC;
	private List<POC7> pocs = new ArrayList<POC7>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public App6() {
		refresh();
	}

	@PostConstruct
	public void init() {
		selectedPOC = new POC7();
	}

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void echo() {
		clearMessage();
		POC7Client p = new POC7Client();
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
		try {
			POC7Client p = new POC7Client();
			p.sendData("update", selectedPOC.getIDTransaksi().toString(), sdf
					.format(selectedPOC.getTanggalTransaksi()), selectedPOC
					.getOriginalAmount().toString(), selectedPOC.getTaxAmount()
					.toString());

			DaoPOC7Impl.update(selectedPOC.getIDTransaksi(),
					new java.sql.Timestamp(selectedPOC.getTanggalTransaksi()
							.getTime()), selectedPOC.getOriginalAmount(),
					selectedPOC.getTaxAmount());
			refresh();
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public void refresh() {
		clearMessage();
		this.selectedPOC = null;
		try {
			pocs = DaoPOC7Impl.read();

		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public POC7 getSelectedPOC() {
		return selectedPOC;
	}

	public void setSelectedPOC(POC7 selectedPOC) {
		this.selectedPOC = selectedPOC;
	}

	public List<POC7> getPocs() {
		return pocs;
	}

	public void setPocs(List<POC7> pocs) {
		this.pocs = pocs;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
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
