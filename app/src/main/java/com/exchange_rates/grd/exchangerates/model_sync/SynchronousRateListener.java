package com.exchange_rates.grd.exchangerates.model_sync;


import com.exchange_rates.grd.exchangerates.model_sync.domain.pojo.Rate;

import java.util.List;

/**
 * Callback for use outside by main thread
 * The bridge for  synchronous callbacks  data from the interactor/repository to presenter
 */
public interface SynchronousRateListener {
    /**
     *  For showing error message in main thread
     */
    void onError();
    /**
     * The provision  synchronous  data from the interactor/repository to presenter
     */
    void onSuccess(List<Rate> data);
}