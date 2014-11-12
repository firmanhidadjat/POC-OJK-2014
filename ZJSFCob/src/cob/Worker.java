package cob;

public class Worker extends Thread {
	String namaThread;
	UtilCounter utilctr;

	public Worker(String namaThread, UtilCounter u) {
		this.namaThread = namaThread;
		this.utilctr = u;
	}

	public Worker(String namaThread) {
		this.namaThread = namaThread;
	}

	public Worker() {

	}

	public void run() {
		this.utilctr.addCounter();
		SendData.kirimData();
		System.out.println("Nama thread = " + this.namaThread
				+ "   JML counter = " + this.utilctr.getCounter());
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		this.utilctr.subtractCounter();
	}
}
