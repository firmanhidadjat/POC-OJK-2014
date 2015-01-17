package ojk.app.login;

import id.co.hanoman.LoggerUtil;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "Login")
@SessionScoped
public class Login implements Serializable {
	static Logger log = LoggerUtil.getLog();
	static final String welcomePage = "App6";

	public Login() {
	}

	private String username;
	private String password;
	private String messageError;
	private boolean errorStatus = false;

	private String usernameCache;
	private String passwordCache;

	@PostConstruct
	public void init() {
		this.username = "";
		this.password = "";
		this.errorStatus = false;
	}

	public void doRedirect(ActionEvent actionEvent) {
		String url = (String) actionEvent.getComponent().getAttributes()
				.get("namaHalaman");
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String Login() {
		this.usernameCache = "";
		this.passwordCache = "";
		String str = "GG";
		try {
			str = LoginClient.login(username, password);

			log.error("HHHHHHHHHHHHHHHHHHHHHHHHHH  " + str);
			if (str.contains("HTTP ( 401 ) Unauthorized address")) {
				this.messageError = "Invalid user name or password";
				this.errorStatus = true;
				log.error("Invalid user name or password");
				this.username = "";
				this.password = "";
				return "Login";
			} else if (str.contains("Forbidden IP Address")) {
				this.messageError = "Forbidden IP Address";
				this.errorStatus = true;
				log.error("Forbidden IP Address");
				this.username = "";
				this.password = "";
				return "Login";
			} else if (str.equalsIgnoreCase("Koneksi Sukses")) {
				this.errorStatus = false;
				this.messageError = "";
				log.error("Koneksi suksessssss");
				this.usernameCache = this.username;
				this.passwordCache = this.password;
				this.username = "";
				this.password = "";
				return this.welcomePage + "?faces-redirect=true";
			} else {
//				this.messageError = "Internal Error";
				this.messageError = str;
				this.errorStatus = true;
				log.error("Internal Error");
				this.username = "";
				this.password = "";
				return "Login";
			}

		} catch (Exception e) {
			if (LoggerUtil.getStackTrace(e).contains(
					"Bad response: (401Unauthorized")) {
				this.messageError = "Invalid user name or password";
				this.errorStatus = true;
				log.error("Invalid user name or password");
				return "Login?faces-redirect=true";
			} else if (LoggerUtil.getStackTrace(e).contains(
					"Forbidden IP Address")) {
				this.messageError = "Forbidden IP Address";
				this.errorStatus = true;
				log.error("Forbidden IP Address");
				return "Login?faces-redirect=true";
			}
		}
		return "Login?faces-redirect=true";
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

	public String getUsernameCache() {
		return usernameCache;
	}

	public void setUsernameCache(String usernameCache) {
		this.usernameCache = usernameCache;
	}

	public String getPasswordCache() {
		return passwordCache;
	}

	public void setPasswordCache(String passwordCache) {
		this.passwordCache = passwordCache;
	}

	// public UserSessionData getUserSessionData() {
	// return UserSessionData;
	// }
}
