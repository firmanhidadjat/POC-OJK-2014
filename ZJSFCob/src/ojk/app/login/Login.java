package ojk.app.login;

import id.co.hanoman.LoggerUtil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean(name = "Login")
@SessionScoped
public class Login implements Serializable {
	static Logger log = LoggerUtil.getLog();

	public Login() {
	}

	// @ManagedProperty(value = "#{UserSessionData}")
	// private UserSessionData UserSessionData;
	//
	// public void setUserData(UserSessionData UserData) {
	// this.UserSessionData = UserData;
	// }

	private String username;
	private String password;
	private String messageError;
	private boolean errorStatus = false;

	@PostConstruct
	public void init() {
		this.username = "";
		this.password = "";
		this.errorStatus = false;
	}

	public void Login() {
		this.messageError = "";
		username = "Budiy";
		password = "PasswordBudi";
		String str = "GG";
		try {
			str = LoginClient.login(username, password);
			log.error("HHHHHHHHHHHHHHHHHHHHHHHHHH  " + str);
			if (str.equalsIgnoreCase("Koneksi Sukses")) {
				log.error("Koneksi suksessssss");
			} else if (str.equalsIgnoreCase("Bad response: (401Unauthorized")) {
				log.error("Invalid user name or password");
			} else if (str.equalsIgnoreCase("Forbidden IP Address")) {
				log.error("Forbidden IP Address");
			} else {
				log.error("Internal Error");
			}
		} catch (Exception e) {
			if (LoggerUtil.getStackTrace(e).contains(
					"Bad response: (401Unauthorized")) {
				log.error("Invalid user name or password");
			} else if (LoggerUtil.getStackTrace(e).contains(
					"Forbidden IP Address")) {
				log.error("Forbidden IP Address");
			}
		}

		// if (str.equalsIgnoreCase("berhasil login")) {
		// log.info(this.username + " berhasil login");
		// // this.UserSessionData.setUsername(this.username);
		// // this.UserSessionData.setPassword(this.password);
		// // this.UserSessionData.setStatus(true);
		// this.errorStatus = false;
		// this.username = "";
		// this.password = "";
		// } else {
		// // this.UserSessionData.setStatus(false);
		// this.messageError = str;
		// this.errorStatus = true;
		// this.username = "";
		// this.password = "";
		// log.error(messageError);
		// }
	}

	private void resetErrorStatus() {
		this.messageError = "";
		this.errorStatus = false;
		this.username = "";
		this.password = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	// public UserSessionData getUserSessionData() {
	// return UserSessionData;
	// }
}
