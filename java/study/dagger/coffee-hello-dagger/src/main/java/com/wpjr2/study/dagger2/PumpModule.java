package com.wpjr2.study.dagger2;

import dagger.Binds;
import dagger.Module;

@Module
abstract class PumpModule {

	@Binds
	abstract Pump providePump(Thermosiphon pump);
	
}
