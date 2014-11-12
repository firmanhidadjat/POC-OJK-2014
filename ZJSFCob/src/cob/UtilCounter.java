package cob;

public class UtilCounter {
	protected int counter = 0;

	public synchronized void addCounter() {
		this.counter++;
	}

	public synchronized void subtractCounter() {
		this.counter--;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
