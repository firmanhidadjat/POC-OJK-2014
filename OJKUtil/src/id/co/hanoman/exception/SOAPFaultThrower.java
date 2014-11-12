package id.co.hanoman.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "SOAPFaultThrower")
public class SOAPFaultThrower extends Exception {
	private FaultInfoDetail faultInfo;

	public SOAPFaultThrower(String message, String messageErrorDetail) {
		super(message);
		this.faultInfo = new FaultInfoDetail(messageErrorDetail);
	}

	public SOAPFaultThrower(String message, FaultInfoDetail faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	public SOAPFaultThrower(String message, FaultInfoDetail faultInfo,
			Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	public SOAPFaultThrower(String message, Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	public SOAPFaultThrower(String message, String messageErrorDetail,
			Throwable cause) {
		super(message, cause);
		this.faultInfo = new FaultInfoDetail(messageErrorDetail);
	}

	public FaultInfoDetail getFaultInfo() {
		return faultInfo;
	}
}
