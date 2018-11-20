package com.exchange_rates.grd.exchangerates.model_sync;


import com.exchange_rates.grd.exchangerates.model_sync.domain.pojo.Rate;

import java.util.List;

/**
 * Callback for use outside by main thread
 */
public interface SynchronousRateListener {
    /**
     *  For showing error message in main thread
     */
    void onError();
    /**
     * The provision  synchronous  data
     */
    void onSuccess(List<Rate> data);
}