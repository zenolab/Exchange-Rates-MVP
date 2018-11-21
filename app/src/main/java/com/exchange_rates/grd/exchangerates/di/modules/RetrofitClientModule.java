package com.exchange_rates.grd.exchangerates.di.modules;


import com.exchange_rates.grd.exchangerates.rest_api.RetrofitCallRateService;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitClientInstance;

import dagger.Module;
import dagger.Provides;


public class RetrofitClientModule {


    RetrofitClientInstance RetrofitClient(){
        return new RetrofitClientInstance();
    }
}
