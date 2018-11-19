package com.exchange_rates.grd.exchangerates.model.repository;

import android.util.Log;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import com.exchange_rates.grd.exchangerates.model.RepositoryCallbackListener;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitCallRateService;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitClientInstance;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepositoryRate implements RateContract.Repository {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    RetrofitCallRateService service;
    Call<List<Rate>> call;

    /**
     * Retrofit Asynchronous request
     */
    public void getRateRetrofitAsynchronous(final RepositoryCallbackListener asyncListener,
                                            final Market market) {

        call = getService(call, market);
            call.enqueue(new Callback<List<Rate>>() {
                @Override
                public void onResponse(Call<List<Rate>> call, Response<List<Rate>> response) {
                    if (response.isSuccessful()) {
                        Log.d(LOG_TAG, "Response " + response.body());
                        asyncListener.onSuccessAnswer(response.body());
                    } else {
                        Log.w(LOG_TAG, "Response Unsuccessful!");
                        Log.e(LOG_TAG, "Response code: " + response.code());
                        asyncListener.onErrorАnswer(Integer.toString(response.code()));
                    }
                }
                @Override
                public void onFailure(Call<List<Rate>> call, Throwable t) {
                    asyncListener.onErrorАnswer(t.getMessage());
                }
            });

    }

    private Call<List<Rate>> getService(Call<List<Rate>> call, final Market market) {

        service = RetrofitClientInstance
                .getRetrofitInstance()
                .create(RetrofitCallRateService.class);

        switch (market) {
            case AllData:
                call = service.fetchData("rate_all.json");
                break;
            case Currency:
                call = service.fetchData("rate_currencies.json");
                break;
            case Commodities:
                call = service.fetchData("rate_commodities.json");
                break;
            case Crypto:
                call = service.fetchData("rate_crypto_currencies.json");
                break;
            case ExchangeShares:
                call = service.fetchData("rate_exchange_shares.json");
                break;
            case Indexes:
                call = service.fetchData("rate_indices.json");
                break;
        }
        return call;
    }

    @Override
    public void getRateRepositoryAsync(
            final RepositoryCallbackListener asyncListener, final Market market) {
        getRateRetrofitAsynchronous(asyncListener,market);
    }
}
