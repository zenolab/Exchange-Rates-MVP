package com.exchange_rates.grd.exchangerates;

import android.app.Application;

import com.exchange_rates.grd.exchangerates.di.component.AppComponent;
import com.exchange_rates.grd.exchangerates.di.component.DaggerAppComponent;


public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
