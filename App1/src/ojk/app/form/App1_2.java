package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import ojk.app.login.Login;
import ojk.app.poc1.client.POC1Client;

@ManagedBean(name = "App1_2")
@RequestScoped
public class App1_2 {
	static Logger log = LoggerUtil.getLog();
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
	
	
	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void echo() {
		clearMessage();
		POC1Client p = new POC1Client();
		try {
			if (!p.echo(login.getUsernameCache(), login.getPasswordCache()).equals("cobaKoneksi")) {
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
//				throw new Exception(e);
			}
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
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
