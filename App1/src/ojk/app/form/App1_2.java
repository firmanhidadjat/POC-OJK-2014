package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import ojk.app.poc1.client.POC1Client;

@ManagedBean(name = "App1_2")
@RequestScoped
public class App1_2 {
	static Logger log = LoggerUtil.getLog();
	private String userName;
	private String password;
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void echo() {
		clearMessage();
		POC1Client p = new POC1Client();
		try {
			if (!p.echo(userName, password).equals("cobaKoneksi")) {
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
		this.userName = null;
		this.password = null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
