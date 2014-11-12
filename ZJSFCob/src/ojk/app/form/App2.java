package ojk.app.form;

import id.co.hanoman.LoggerUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean(name = "App2")
// @RequestScoped
@SessionScoped
public class App2 {
	static Logger log = LoggerUtil.getLog();

	private BigDecimal TransactionID;
	private String FirstName;
	private String LastName;
	private String Notes;
	private String Status;
	private BigDecimal TransactionValue;
	private BigDecimal ApprovedAmount;
	private BigDecimal Pin;
	private POC2 selectedPOC;
	private String strMessageLong;
	private String strMessage;
	private boolean kondisiWarn = false;

	private List<POC2> pocs = new ArrayList<POC2>();

	public App2() {
		refresh();
	}

	public void clearMessage() {
		this.strMessage = null;
		this.strMessageLong = null;
		this.kondisiWarn = false;
	}

	public void dodol() throws InterruptedException {
		Thread.sleep(2000);
	}

	public void submit() {

		clearMessage();
		System.out.println("================================================");
		System.out.println("Transaction ID = "
				+ this.selectedPOC.getTransactionID());
		System.out.println("First Name = " + this.selectedPOC.getFirstName());
		System.out.println("Last Name = " + this.selectedPOC.getLastName());
		System.out.println("Status = " + this.selectedPOC.getStatus());
		System.out.println("Notes = " + this.selectedPOC.getNotes());
		System.out.println("Transaction Value = "
				+ this.selectedPOC.getTransactionValue());
		System.out.println("Approved Amount = "
				+ this.selectedPOC.getApprovedAmount());
		System.out.println("PIN = " + this.selectedPOC.getPin());
		System.out.println("------------------------------------------------");
		try {

			this.strMessage = (new BigDecimal(this.selectedPOC.getStatus())
					.divide(this.selectedPOC.getApprovedAmount())).toString();
		} catch (Exception e) {
			this.strMessage = e.getMessage();
			this.strMessageLong = LoggerUtil.getStackTrace(e);
			this.kondisiWarn = true;
		}
	}

	public void refresh() {
		if (pocs != null) {
			if (pocs.size() != 0) {
				pocs.clear();
			}
		}
		pocs.add(new POC2(new BigDecimal("1"), "Budi", "Anduk", "Keterangan 1",
				"", new BigDecimal("111"), new BigDecimal("0"), new BigDecimal(
						"111")));
		pocs.add(new POC2(new BigDecimal("2"), "BBB", "BB2", "Keterangan 2",
				"", new BigDecimal("222"), new BigDecimal("0"), new BigDecimal(
						"222")));
		pocs.add(new POC2(new BigDecimal("3"), "CCC", "CC2", "Keterangan 2",
				"", new BigDecimal("333"), new BigDecimal("333"),
				new BigDecimal("333")));
		pocs.add(new POC2(new BigDecimal("4"), "DDD", "DD2", "Keterangan 3",
				"", new BigDecimal("333"), new BigDecimal("0"), new BigDecimal(
						"333")));
		pocs.add(new POC2(new BigDecimal("5"), "EEE", "EE2", "Keterangan 4",
				"", new BigDecimal("444"), new BigDecimal("0"), new BigDecimal(
						"444")));
		pocs.add(new POC2(new BigDecimal("6"), "FFF", "FF2", "Keterangan 6",
				"", new BigDecimal("666"), new BigDecimal("0"), new BigDecimal(
						"666")));
		pocs.add(new POC2(new BigDecimal("7"), "GGG", "GG2", "Keterangan 7",
				"", new BigDecimal("777"), new BigDecimal("0"), new BigDecimal(
						"777")));

	}

	public List<POC2> getPocs() {
		return pocs;
	}

	public void setPocs(List<POC2> pocs) {
		this.pocs = pocs;
	}

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

	public BigDecimal getPin() {
		return Pin;
	}

	public void setPin(BigDecimal pin) {
		Pin = pin;
	}

	public POC2 getSelectedPOC() {
		return selectedPOC;
	}

	public void setSelectedPOC(POC2 selectedPOC) {
		this.selectedPOC = selectedPOC;
	}

	public String getStrMessageLong() {
		return strMessageLong;
	}

	public void setStrMessageLong(String strMessageLong) {
		this.strMessageLong = strMessageLong;
	}

	public String getStrMessage() {
		return strMessage;
	}

	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}

	public boolean isKondisiWarn() {
		return kondisiWarn;
	}

	public void setKondisiWarn(boolean kondisiWarn) {
		this.kondisiWarn = kondisiWarn;
	}

}
