package com.exchange_rates.grd.exchangerates.model;

import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface RepositoryListener {
    void onSuccessAnswer(List<Rate> data);
    void subscription(Disposable disposable);
    void onErrorАnswer(String string);

}
