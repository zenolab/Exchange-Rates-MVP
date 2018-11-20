package com.exchange_rates.grd.exchangerates.rest_api;



import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RetrofitCallRateService {

    @Headers("Content-Type: application/json")
    @GET("android-folder/public-json/market-rate/{market}")
   // Observable<List <Rate> > fetchData(@Path("market") String name);
    Single<List <Rate> > fetchData(@Path("market") String name);
}
