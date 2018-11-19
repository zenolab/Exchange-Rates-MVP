package com.exchange_rates.grd.exchangerates.model_async;

import com.exchange_rates.grd.exchangerates.model_async.domain.interactor.pojo.Rate;

import java.util.List;

public interface RepositoryCallbackListener {
    void onSuccessAnswer(List<Rate> data);
    void onErrorCodeAnswer(int code);
    void onErrorАnswer(String string);

}
