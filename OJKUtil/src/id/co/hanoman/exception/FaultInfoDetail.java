package id.co.hanoman.exception;

public class FaultInfoDetail {
	private String message;

	public FaultInfoDetail() {
	}

	public FaultInfoDetail(String pesanDetail) {
		this.message = pesanDetail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
