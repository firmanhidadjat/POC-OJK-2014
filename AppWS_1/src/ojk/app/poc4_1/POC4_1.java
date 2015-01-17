package ojk.app.poc4_1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "IDTransaksi", "TanggalTransaksi", "lokasi" })
public class POC4_1 implements Serializable {

	public POC4_1() {

	}

	public POC4_1(BigDecimal IDTransaksi, Date TanggalTransaksi, String lokasi) {
		this.IDTransaksi = IDTransaksi;
		this.TanggalTransaksi = TanggalTransaksi;
		this.lokasi = lokasi;
	}

	@XmlElement(name = "IDTransaksi")
	private BigDecimal IDTransaksi;
	@XmlElement(name = "TanggalTransaksi")
	private Date TanggalTransaksi;
	@XmlElement(name = "Lokasi")
	private String lokasi;

	public BigDecimal getIDTransaksi() {
		return IDTransaksi;
	}

	public void setIDTransaksi(BigDecimal iDTransaksi) {
		IDTransaksi = iDTransaksi;
	}

	public Date getTanggalTransaksi() {
		return TanggalTransaksi;
	}

	public void setTanggalTransaksi(Date tanggalTransaksi) {
		TanggalTransaksi = tanggalTransaksi;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

}
