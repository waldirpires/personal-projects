package ericsson.adpoint.test;

public class Port {
	private int number;
	private boolean isOpen;
	public Port(int number) {
		super();
		this.number = number;
	}	
	
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public int getNumber() {
		return number;
	}
}
