package cob;

public class MainClass {
	public static void main(String[] ar) throws InterruptedException {
		UtilCounter u = new UtilCounter();
		// for (int i = 0; i < 10; i++) {
		// new Worker(String.valueOf(i), u).start();
		// }
		int i = 0;
		while (true) {
			i++;
			if (u.getCounter() < 10) {
				
				new Worker(String.valueOf(i), u).start();
			}
//			System.out.println("ddd = "+u.getCounter());
//			Thread.sleep(1000);
		}

	}
}
