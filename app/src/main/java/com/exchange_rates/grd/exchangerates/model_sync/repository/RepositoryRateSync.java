package com.exchange_rates.grd.exchangerates.model_sync.repository;

import android.util.Log;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.model_sync.domain.pojo.Rate;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitCallRateService;
import com.exchange_rates.grd.exchangerates.rest_api.RetrofitClientInstance;


import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class RepositoryRateSync implements RateContract.Repository {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    RetrofitCallRateService service;
    Call<List<Rate>> call;

    public RepositoryRateSync(){}

    /**
     * Retrofit Synchronous request
     */
    public List<Rate> getRateRetrofitSynchronous(Market market) throws IOException {

            List<Rate> jsonData =null ;
            call = getService(call, market);
            jsonData = call.execute().body();
            Log.d(LOG_TAG, "Response Synchronous is === " + jsonData);
            return jsonData;
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
    public List<Rate> getRateRepositoryRateSync(final Market market) throws IOException {
        return getRateRetrofitSynchronous(market) ;
    }
}
