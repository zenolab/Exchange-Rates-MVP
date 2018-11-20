package com.exchange_rates.grd.exchangerates.model_async;

import com.exchange_rates.grd.exchangerates.model_async.domain.interactor.pojo.Rate;

import java.util.List;


public interface AsyncRateListenerOfInteractor {

    /**
     * For showing error code
     * @param code
     */
     void onErrorCodeAsync(int code);
    /**
     * Receiving data
     *  @param data
     */
    void onSuccessAsync(List<Rate> data);


    /**
     * For showing error message in main thread
     * @param message
     */
    void onErrorMessageAsync(String message);
}
