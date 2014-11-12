package ojk.app.poc5;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "IDTransaksi", "TanggalTransaksi", "OriginalAmount",
		"ApprovedAmount", "TaxAmount" })
public class POC5 implements Serializable {

	public POC5() {

	}

	public POC5(BigDecimal IDTransaksi, Date TanggalTransaksi,
			BigDecimal OriginalAmount, BigDecimal ApprovedAmount,
			BigDecimal TaxAmount) {
		this.IDTransaksi = IDTransaksi;
		this.TanggalTransaksi = TanggalTransaksi;
		this.OriginalAmount = OriginalAmount;
		this.ApprovedAmount = ApprovedAmount;
		this.TaxAmount = TaxAmount;
	}

	@XmlElement(name = "IDTransaksi")
	private BigDecimal IDTransaksi;
	@XmlElement(name = "TanggalTransaksi")
	private Date TanggalTransaksi;
	@XmlElement(name = "OriginalAmount")
	private BigDecimal OriginalAmount;
	@XmlElement(name = "ApprovedAmount")
	private BigDecimal ApprovedAmount;
	@XmlElement(name = "TaxAmount")
	private BigDecimal TaxAmount;

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

	public BigDecimal getOriginalAmount() {
		return OriginalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {
		OriginalAmount = originalAmount;
	}

	public BigDecimal getApprovedAmount() {
		return ApprovedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		ApprovedAmount = approvedAmount;
	}

	public BigDecimal getTaxAmount() {
		return TaxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		TaxAmount = taxAmount;
	}

}
