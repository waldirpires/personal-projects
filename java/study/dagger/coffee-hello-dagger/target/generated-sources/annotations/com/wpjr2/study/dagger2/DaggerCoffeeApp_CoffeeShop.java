package com.wpjr2.study.dagger2;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerCoffeeApp_CoffeeShop implements CoffeeApp.CoffeeShop {
  private Provider<Heater> provideHeaterProvider;

  private Provider<Thermosiphon> thermosiphonProvider;

  private Provider<Pump> pumpProvider;

  private Provider<CoffeeMaker> coffeeMakerProvider;

  private DaggerCoffeeApp_CoffeeShop(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static CoffeeApp.CoffeeShop create() {
    return builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideHeaterProvider =
        DoubleCheck.provider(
            DripCoffeeModule_ProvideHeaterFactory.create(builder.dripCoffeeModule));

    this.thermosiphonProvider = Thermosiphon_Factory.create(provideHeaterProvider);
    this.pumpProvider = (Provider) thermosiphonProvider;

    this.coffeeMakerProvider = CoffeeMaker_Factory.create(provideHeaterProvider, pumpProvider);
  }

  @Override
  public CoffeeMaker maker() {
    return coffeeMakerProvider.get();
  }

  public static final class Builder {
    private DripCoffeeModule dripCoffeeModule;

    private PumpModule pumpModule;

    private Builder() {}

    public CoffeeApp.CoffeeShop build() {
      if (dripCoffeeModule == null) {
        this.dripCoffeeModule = new DripCoffeeModule();
      }
      return new DaggerCoffeeApp_CoffeeShop(this);
    }

    public Builder dripCoffeeModule(DripCoffeeModule dripCoffeeModule) {
      this.dripCoffeeModule = Preconditions.checkNotNull(dripCoffeeModule);
      return this;
    }

    public Builder pumpModule(PumpModule pumpModule) {
      this.pumpModule = Preconditions.checkNotNull(pumpModule);
      return this;
    }
  }
}
