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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import ojk.app.login.Login;
import ojk.app.poc3.POC3;
import ojk.dao.impl.DaoPOC3Impl;

@ManagedBean(name = "App3")
// @RequestScoped
@ViewScoped
public class App3 implements Serializable {
	static Logger log = LoggerUtil.getLog();

	private POC3 selectedPOC;
	private List<POC3> pocs = new ArrayList<POC3>();
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public App3() {
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
		selectedPOC = new POC3();
	}

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void refresh() {
		clearMessage();
		this.selectedPOC = null;
		try {
			pocs = DaoPOC3Impl.read();
		} catch (Exception e) {
			log.error(LoggerUtil.getStackTrace(e));
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public List<POC3> getPocs() {
		return pocs;
	}

	public void setPocs(List<POC3> pocs) {
		this.pocs = pocs;
	}

	public POC3 getSelectedPOC() {
		return selectedPOC;
	}

	public void setSelectedPOC(POC3 selectedPOC) {
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
