package com.exchange_rates.grd.exchangerates.rest_api;



import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RetrofitCallRateService {


    @Headers("Content-Type: application/json")
    @GET("android-folder/public-json/market-rate/{market}")
  //  @GET("android-folder/public-json/market-rate/{market}")
  //  Call<List <Rate> > fetchData(@Path("market") String name);
    Observable<List <Rate> > fetchData(@Path("market") String name);

  //  @GET("android-folder/public-json/market-rate/")
  //  Observable<List <Rate> > fetchData();


}
