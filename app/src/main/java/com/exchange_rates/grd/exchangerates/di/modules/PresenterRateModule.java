package com.exchange_rates.grd.exchangerates.di.modules;

import com.exchange_rates.grd.exchangerates.rest_api.RetrofitCallRateService;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.foreign_currency.CurrencyPresenterImp;

import dagger.Module;
import dagger.Provides;


public class PresenterRateModule {

    CurrencyPresenterImp providerPresenterRate(){
         return new CurrencyPresenterImp();
    }
}
