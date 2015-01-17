package ojk.app.poc4_1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "IDTransaksi", "Status", "lokasi" })
public class POC4_1Result {

	public POC4_1Result() {

	}

	public POC4_1Result(BigDecimal IDTransaksi, String Status, String lokasi) {
		this.IDTransaksi = IDTransaksi;
		this.Status = Status;
		this.lokasi = lokasi;
	}

	@XmlElement(name = "IDTransaksi")
	private BigDecimal IDTransaksi;

	@XmlElement(name = "Status")
	private String Status;

	@XmlElement(name = "Lokasi")
	private String lokasi;

	public BigDecimal getIDTransaksi() {
		return IDTransaksi;
	}

	public void setIDTransaksi(BigDecimal iDTransaksi) {
		IDTransaksi = iDTransaksi;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

}
