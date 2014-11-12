package ojk.app.poc1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "IDTransaksi", "FullName", "Keterangan", "Status", "NilaiTransaksi",
		"NilaiDisetujui", "Pin" })
public class POC1 {
	
	public POC1(){
		
	}
	public POC1(BigDecimal IDTransaksi, String FullName, String Keterangan, String Status,
			BigDecimal NilaiTransaksi, BigDecimal NilaiDisetujui, BigDecimal Pin) {
		this.IDTransaksi = IDTransaksi;
		this.FullName = FullName;
		this.Keterangan = Keterangan;
		this.Status = Status;
		this.NilaiTransaksi = NilaiTransaksi;
		this.NilaiDisetujui = NilaiDisetujui;
		this.Pin = Pin;
	}

	@XmlElement(name = "IDTransaksi")
	private BigDecimal IDTransaksi;
	@XmlElement(name = "FullName")
	private String FullName;
	@XmlElement(name = "Keterangan")
	private String Keterangan;
	@XmlElement(name = "Status")
	private String Status;
	@XmlElement(name = "NilaiTransaksi")
	private BigDecimal NilaiTransaksi;
	@XmlElement(name = "NilaiDisetujui")
	private BigDecimal NilaiDisetujui;
	@XmlElement(name = "Pin")
	private BigDecimal Pin;

	public BigDecimal getIDTransaksi() {
		return IDTransaksi;
	}

	public void setIDTransaksi(BigDecimal iDTransaksi) {
		IDTransaksi = iDTransaksi;
	}

	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getKeterangan() {
		return Keterangan;
	}

	public void setKeterangan(String keterangan) {
		Keterangan = keterangan;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public BigDecimal getNilaiTransaksi() {
		return NilaiTransaksi;
	}

	public void setNilaiTransaksi(BigDecimal nilaiTransaksi) {
		NilaiTransaksi = nilaiTransaksi;
	}

	public BigDecimal getNilaiDisetujui() {
		return NilaiDisetujui;
	}

	public void setNilaiDisetujui(BigDecimal nilaiDisetujui) {
		NilaiDisetujui = nilaiDisetujui;
	}

	public BigDecimal getPin() {
		return Pin;
	}

	public void setPin(BigDecimal pin) {
		Pin = pin;
	}

}
