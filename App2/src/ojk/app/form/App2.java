package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import ojk.app.login.Login;
import ojk.app.poc2.POC2;
import ojk.app.poc2.client.POC2Client;
import ojk.dao.impl.DaoPOC2Impl;

@ManagedBean(name = "App2")
// @RequestScoped
@ViewScoped
public class App2 implements Serializable {
	static Logger log = LoggerUtil.getLog();
	private POC2 selectedPOC;
	private List<POC2> pocs = new ArrayList<POC2>();
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public App2() {
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
		selectedPOC = new POC2();
	}

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void submit() {
		clearMessage();
		POC2Client p = new POC2Client();
		try {
			// p.echo();
			if (this.selectedPOC.getNotes() == null
					|| this.selectedPOC.getNotes().equals("")) {
				this.selectedPOC.setNotes(" ");
			}

			p.sendData(login.getUsernameCache(), login.getPasswordCache(),
					"update", this.selectedPOC.getTransactionID().toString(),
					this.selectedPOC.getFirstName(), this.selectedPOC
							.getLastName(), this.selectedPOC.getNotes(),
					this.selectedPOC.getStatus().toUpperCase(),
					this.selectedPOC.getTransactionValue().toString(),
					this.selectedPOC.getApprovedAmount().toString(),
					this.selectedPOC.getPin().toString());
			DaoPOC2Impl.update(this.selectedPOC.getTransactionID(),
					this.selectedPOC.getFirstName(), this.selectedPOC
							.getLastName(), this.selectedPOC.getNotes(),
					this.selectedPOC.getStatus().toUpperCase(),
					this.selectedPOC.getTransactionValue(), this.selectedPOC
							.getApprovedAmount(), this.selectedPOC.getPin());
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
		this.selectedPOC = null;
		try {
			pocs = DaoPOC2Impl.read();
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public List<POC2> getPocs() {
		return pocs;
	}

	public void setPocs(List<POC2> pocs) {
		this.pocs = pocs;
	}

	public POC2 getSelectedPOC() {
		return selectedPOC;
	}

	public void setSelectedPOC(POC2 selectedPOC) {
		this.selectedPOC = selectedPOC;
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
