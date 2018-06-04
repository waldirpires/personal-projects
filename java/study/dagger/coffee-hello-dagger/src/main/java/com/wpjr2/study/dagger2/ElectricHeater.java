package com.wpjr2.study.dagger2;

public class ElectricHeater implements Heater{
	
	boolean heating;

	public boolean isHot() {
		return heating;
	}

	public void on() {
		System.out.println("--- heating ---");
		this.heating = true;
		
	}

	public void off() {
		this.heating = false;		
	}

}
