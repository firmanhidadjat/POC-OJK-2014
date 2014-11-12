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
import ojk.app.poc6.POC6;
import ojk.app.poc6.client.POC6Client;
import ojk.dao.impl.DaoPOC6Impl;

@ManagedBean(name = "App5")
@ViewScoped
// @SessionScoped
public class App5 implements Serializable {
	static Logger log = LoggerUtil.getLog();

	private POC6 selectedPOC;
	private List<POC6> pocs = new ArrayList<POC6>();
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public App5() {
		refresh();
	}

	@PostConstruct
	public void init() {
		selectedPOC = new POC6();
	}

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	public void echo() {
		clearMessage();
		POC6Client p = new POC6Client();
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
			POC6Client p = new POC6Client();
			p.sendData("update", selectedPOC.getIDTransaksi().toString(), sdf
					.format(selectedPOC.getTanggalTransaksi()), selectedPOC
					.getOriginalAmount().toString(), selectedPOC
					.getApprovedAmount().toString());
			DaoPOC6Impl.update(selectedPOC.getIDTransaksi(),
					new java.sql.Timestamp(selectedPOC.getTanggalTransaksi()
							.getTime()), selectedPOC.getOriginalAmount(),
					selectedPOC.getApprovedAmount());
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
			pocs = DaoPOC6Impl.read();
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public POC6 getSelectedPOC() {
		return selectedPOC;
	}

	public void setSelectedPOC(POC6 selectedPOC) {
		this.selectedPOC = selectedPOC;
	}

	public List<POC6> getPocs() {
		return pocs;
	}

	public void setPocs(List<POC6> pocs) {
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
