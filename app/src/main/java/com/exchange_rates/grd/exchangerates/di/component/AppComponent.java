package com.exchange_rates.grd.exchangerates.di.component;

import com.exchange_rates.grd.exchangerates.di.Utils;
import com.exchange_rates.grd.exchangerates.di.modules.UtilsModule;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitClientInstance;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.foreign_currency.CurrencyFragmentView;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.foreign_currency.CurrencyPresenterImp;

import dagger.Component;


@Component(modules = { UtilsModule.class})
public interface AppComponent {

    Utils getNetworkUtils();

}