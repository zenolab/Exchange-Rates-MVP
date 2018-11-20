package com.exchange_rates.grd.exchangerates.model.repository;

import android.support.annotation.Nullable;
import android.util.Log;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import com.exchange_rates.grd.exchangerates.model.RepositoryListener;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitCallRateService;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitClientInstance;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RepositoryRate implements RateContract.Repository {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    /**
     * Retrofit request with RxJava
     */
    public void getRateRetrofitAsynchronous(final RepositoryListener listener,
                                            final Market market) {

        RetrofitCallRateService service = RetrofitClientInstance
                .getRetrofitInstance()
                .create(RetrofitCallRateService.class);

        service.fetchData(makeOut(market))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Rate>>() {
                    @Override
                    public void onNext(List<Rate> data) {
                        Log.d(LOG_TAG, "Response " + data.size());
                        listener.onSuccessAnswer(data);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "Response " + e.getMessage());
                        listener.onErrorАnswer(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        Log.d(LOG_TAG, "Response complete");
                    }
                    @Override
                    public void onSubscribe(Disposable disposable ) {
                        Log.d(LOG_TAG, "Subscribe disposable is"+disposable .isDisposed());
                        destroyRepository(disposable);
                        listener.subscription(disposable);
                    }
                });

                /*
                //----------------Lambda-------------------
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (response) -> {
                            Log.d(LOG_TAG, "Response Success");
                            listener.onSuccessAnswer(response);
                        },
                        (err) -> {
                            Log.d(LOG_TAG, "Response error " + err.getMessage());
                            listener.onErrorАnswer(err.getMessage());

                        });
                */
                //-----------Functional interface-----------
               // .subscribe(this::handleResults, this::handleError);
    }
    /**
     * distinguish/recognize market (make out market)
     */
    private String makeOut(Market market) {
        switch (market) {
            case AllData:
                return "rate_all.json";
            case Currency:
                return  "rate_currencies.json";
            case Commodities:
                return "rate_commodities.json";
            case Crypto:
                return "rate_crypto_currencies.json";
            case ExchangeShares:
                return "rate_exchange_shares.json";
            case Indexes:
                return "rate_indices.json";
        }
        return null;
    }
    @Override
    public void getRateRepositoryAsync(
            final RepositoryListener asyncListener, final Market market) {
        getRateRetrofitAsynchronous(asyncListener,market);
    }
    @Nullable
    @Override
    public <T> T destroyRepository(T... n) {
        Log.d(LOG_TAG, "Subscribe disposable is"+n);
        return null;
    }
}
