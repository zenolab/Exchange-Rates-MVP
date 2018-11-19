package com.exchange_rates.grd.exchangerates.model_async;

import com.exchange_rates.grd.exchangerates.Rate;

import java.util.List;

public interface RepositoryCallbackListener {
    void onSuccessAnswer(List<Rate> data);
    void onErrorCodeAnswer(int code);
    void onErrorАnswer(String string);


    //--------------------------------------------------
//    /**
//     * For showing error code
//     * @param code
//     */
//    void onErrorCodeAsync(int code);
//    /**
//     * The provision  asynchronous  data from the repository to interactor/presenter
//     */
//    void onSuccessAsync(List<Rate> data);
//
//
//    /**
//     * For showing error message in main thread from repository api
//     * @param message
//     */
//    void onErrorMessageAsync(String message);
    //--------------------------------------------------
}
