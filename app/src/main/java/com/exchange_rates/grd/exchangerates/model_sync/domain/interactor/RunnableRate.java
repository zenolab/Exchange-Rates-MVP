package com.exchange_rates.grd.exchangerates.model_sync.domain.interactor;

import com.exchange_rates.grd.exchangerates.model_sync.domain.pojo.Rate;

import java.util.List;

public abstract class RunnableRate implements Runnable {

    private volatile List<Rate> parameter;

    public RunnableRate(List<Rate> parameter){
        this.parameter = parameter;

    }

}