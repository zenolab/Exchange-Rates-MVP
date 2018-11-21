package com.exchange_rates.grd.exchangerates.di.modules;

import com.exchange_rates.grd.exchangerates.di.Utils;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    Utils provideUtils() {
        return new Utils();
    }

}