package ojk.app.poc4esb;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "IDTransaksi", "Status" })
public class POC4Result {

	public POC4Result() {

	}

	public POC4Result(BigDecimal IDTransaksi, String Status) {
		this.IDTransaksi = IDTransaksi;
		this.Status = Status;
	}

	@XmlElement(name = "IDTransaksi")
	private BigDecimal IDTransaksi;

	@XmlElement(name = "Status")
	private String Status;

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

}
