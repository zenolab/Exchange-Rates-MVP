package com.exchange_rates.grd.exchangerates.di;

import dagger.Component;

@Component(modules = { NetworkModule.class})
public interface AppComponent {

    NetworkUtils getNetworkUtils();

}