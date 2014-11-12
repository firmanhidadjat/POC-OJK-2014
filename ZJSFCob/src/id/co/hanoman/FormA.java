package id.co.hanoman;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "formA")
@RequestScoped
public class FormA {
	private String idTransaksi;
	private Date tanggalTransaksi;
	private String status;

	public void kirimData(ActionEvent actionEvent) {
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHhh");
		System.out.println(this.idTransaksi
				+ " ==================================");
		this.idTransaksi = "";
		this.tanggalTransaksi = new Date();
	}

	public void getData() {
		this.idTransaksi = "JOJON";
		this.tanggalTransaksi = new Date();
		this.status = "SAKSES";
	}

	public String getIdTransaksi() {
		return idTransaksi;
	}

	public void setIdTransaksi(String idTransaksi) {
		this.idTransaksi = idTransaksi;
	}

	public Date getTanggalTransaksi() {
		return tanggalTransaksi;
	}

	public void setTanggalTransaksi(Date tanggalTransaksi) {
		this.tanggalTransaksi = tanggalTransaksi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
