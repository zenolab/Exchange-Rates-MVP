package com.exchange_rates.grd.exchangerates.rest_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    private static Retrofit retrofit;
    private static final String BASE_URL = "http://ecocolor.in.ua/";



    //------------------ OkHttpClient Interceptor-------------------
    static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //retrofit2 factory adapter
                   // .addConverterFactory(GsonConverterFactory.create())
                    //rxJava Factory adapter
                   // .addConverterFactory(RxJava2CallAdapterFactory.create()) // Converter Factory
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Call Adapter Factory
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}