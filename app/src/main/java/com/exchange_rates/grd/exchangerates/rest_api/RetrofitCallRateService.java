package com.exchange_rates.grd.exchangerates.rest_api;



import com.exchange_rates.grd.exchangerates.model_async.domain.interactor.pojo.Rate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RetrofitCallRateService {

    @Headers("Content-Type: application/json")
    @GET("android-folder/public-json/market-rate/{market}")
    Call<List <Rate> > fetchData(@Path("market") String name);


}
