package ojk.app.poc2;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "TransactionID", "FirstName", "LastName", "Notes",
		"Status", "TransactionValue", "ApprovedAmount", "Pin" })
public class POC2 implements Serializable {

	public POC2() {

	}

	public POC2(BigDecimal TransactionID, String FirstName, String LastName,
			String Notes, String Status, BigDecimal TransactionValue,
			BigDecimal ApprovedAmount, String Pin) {
		this.TransactionID = TransactionID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Notes = Notes;
		this.Status = Status;
		this.TransactionValue = TransactionValue;
		this.ApprovedAmount = ApprovedAmount;
		this.Pin = Pin;
	}

	@XmlElement(name = "TransactionID")
	private BigDecimal TransactionID;
	@XmlElement(name = "FirstName")
	private String FirstName;
	@XmlElement(name = "LastName")
	private String LastName;
	@XmlElement(name = "Notes")
	private String Notes;
	@XmlElement(name = "Status")
	private String Status;
	@XmlElement(name = "TransactionValue")
	private BigDecimal TransactionValue;
	@XmlElement(name = "ApprovedAmount")
	private BigDecimal ApprovedAmount;
	@XmlElement(name = "Pin")
	private String Pin;

	public BigDecimal getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(BigDecimal transactionID) {
		TransactionID = transactionID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public BigDecimal getTransactionValue() {
		return TransactionValue;
	}

	public void setTransactionValue(BigDecimal transactionValue) {
		TransactionValue = transactionValue;
	}

	public BigDecimal getApprovedAmount() {
		return ApprovedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		ApprovedAmount = approvedAmount;
	}

	public String getPin() {
		return Pin;
	}

	public void setPin(String pin) {
		Pin = pin;
	}

}
