package com.exchange_rates.grd.exchangerates.rest_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    private static Retrofit retrofit;
    // private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String BASE_URL = "http://ecocolor.in.ua/";
    //private static final String BASE_URL = "http://ecocolor.in.ua";


    //------------------ OkHttpClient Interceptor-------------------
    //HttpLoggingInterceptor используется для регистрации данных во время сетевого вызова.
    static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            //.cache(cache)
            .build();
    //-------------------------------------


    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getRetrofitInstance() {
   // public static Retrofit getRetrofitInstance(OkHttpClient okHttpClient) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                   // .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}