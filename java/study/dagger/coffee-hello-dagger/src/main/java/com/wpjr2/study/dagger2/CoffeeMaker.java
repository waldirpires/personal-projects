package com.wpjr2.study.dagger2;

import javax.inject.Inject;

import dagger.Lazy;

public class CoffeeMaker {
	private final Lazy<Heater> heater;
	private final Pump pump;
	
	@Inject CoffeeMaker(Lazy<Heater> heater, Pump pump)
	{
		this.heater = heater;
		this.pump = pump;
	}
	
	public void brew()
	{
		heater.get().on();
		pump.pump();
		System.out.println(" [_]P coffee! [_]P");
		heater.get().off();
	}

}
