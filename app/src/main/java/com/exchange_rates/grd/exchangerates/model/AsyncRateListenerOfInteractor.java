package com.exchange_rates.grd.exchangerates.model;

import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import java.util.List;

/**
 * The bridge for  asynchronous callbacks  data from the repository
 */
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
