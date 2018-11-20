package com.exchange_rates.grd.exchangerates.model;

import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import java.util.List;

import io.reactivex.disposables.Disposable;


public interface InteractorListener {

    /**
     * @param disposable
     */
     void modelState(Disposable disposable);
    /**
     *  @param data
     */
    void onSuccess(List<Rate> data);
    /**
     * @param message
     */
    void onErrorMessage(String message);
}
