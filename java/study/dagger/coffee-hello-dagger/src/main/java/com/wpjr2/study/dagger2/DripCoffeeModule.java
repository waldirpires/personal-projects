package com.wpjr2.study.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = PumpModule.class)
class DripCoffeeModule {
	
	@Provides
	@Singleton
	Heater provideHeater()
	{
		return new ElectricHeater();
	}

}
