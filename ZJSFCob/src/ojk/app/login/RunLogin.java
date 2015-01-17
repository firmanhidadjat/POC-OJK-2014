package ojk.app.login;

import id.co.hanoman.LoggerUtil;
import org.apache.log4j.Logger;

public class RunLogin {
	static Logger log = LoggerUtil.getLog();

	public static void main(String[] ar) throws Exception {
		Login d = new Login();
		d.Login();

	}
}
