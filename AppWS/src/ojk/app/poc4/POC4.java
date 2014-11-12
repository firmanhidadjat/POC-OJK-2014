package ojk.app.poc4;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "IDTransaksi", "TanggalTransaksi" })
public class POC4 implements Serializable {

	public POC4() {

	}

	public POC4(BigDecimal IDTransaksi, Date TanggalTransaksi) {
		this.IDTransaksi = IDTransaksi;
		this.TanggalTransaksi = TanggalTransaksi;
	}

	@XmlElement(name = "IDTransaksi")
	private BigDecimal IDTransaksi;
	@XmlElement(name = "TanggalTransaksi")
	private Date TanggalTransaksi;

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

}
