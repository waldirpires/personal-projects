package com.wpjr2.study.dagger2;

import javax.inject.Singleton;

import dagger.Component;

public class CoffeeApp {

	@Singleton
	@Component(modules= {DripCoffeeModule.class})
	public interface CoffeeShop{
		CoffeeMaker maker();
	}
	
	public static void main(String[] args) {
		CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
		coffeeShop.maker().brew();
	}
}
