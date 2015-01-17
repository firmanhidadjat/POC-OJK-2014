package ojk.app.login;

import id.co.hanoman.LoggerUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean(name = "BacaFile")
@SessionScoped
public class BacaFile {
	static Logger log = LoggerUtil.getLog();

	private String namaServer;

	public static void tulisFile(String teks, String namaFile) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(namaFile, true)));
			out.println(teks);
			out.close();
		} catch (IOException e) {
			log.error("Gagal menulis ke file " + namaFile);
			e.printStackTrace();
		}
	}

	@PostConstruct
	// Method baca file
	public void bacaFile() {
		String namaFile = "/home/aaasss/namaAppServer";
		BufferedReader br = null;
		String stringHasil = "";

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(namaFile));
			while ((sCurrentLine = br.readLine()) != null) {
				stringHasil = stringHasil + sCurrentLine + "\n";
			}

		} catch (IOException e) {
			log.error("Gagal membaca file " + namaFile);
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		this.namaServer = stringHasil;
	}

	public String getNamaServer() {
		return namaServer;
	}

	public void setNamaServer(String namaServer) {
		this.namaServer = namaServer;
	}

}
