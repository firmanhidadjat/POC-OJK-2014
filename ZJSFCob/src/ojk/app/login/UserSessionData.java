package ojk.app.login;

import id.co.hanoman.LoggerUtil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean(name = "UserSessionData")
@SessionScoped
public class UserSessionData implements Serializable {
	static Logger log = LoggerUtil.getLog();

	public UserSessionData() {

	}

	@PostConstruct
	public void init() {
		this.status = false;
	}

	private String username;
	private String password;
	private boolean status = false;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
