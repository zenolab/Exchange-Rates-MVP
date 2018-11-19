package com.exchange_rates.grd.exchangerates.model;

import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import java.util.List;

public interface RepositoryCallbackListener {
    void onSuccessAnswer(List<Rate> data);
    void onErrorCodeAnswer(int code);
    void onErrorАnswer(String string);

}
