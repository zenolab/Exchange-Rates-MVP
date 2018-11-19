package com.exchange_rates.grd.exchangerates.rest_api;



import com.exchange_rates.grd.exchangerates.Rate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RetrofitCallRateService {

    //Calls may be executed synchronously with {@link #execute}, or asynchronously with {@link
    // * #enqueue}.
    //Cloneable пустой интерфейс маркер - для переопределения copy() защищенных классов protected через рефлексию и пространство класс Object лежит в java.lang
   /*
    У объекта java.lang.Object есть метод protected Object clone() throws CloneNotSupportedException.
    Этот метод позволяет создавать копии объектов.
    Он бросает исключение CloneNotSupportedException для всех объектов,
    которые не реализуют интерфейс java.lang.Cloneable,
    а для классов, реализующих этот интерфейс, возвращает копию объекта,
    которая создана копированием всех полей исходного объекта.
    Содержимое полей не копируется. Если поле является ссылочным,
     то оно будет указывать на тот же самый объект, что и исходное поле.

    Во многих случаях простого копирования недостаточно,
     так как некоторые поля могут содержать сложные структуры, для которых нужно создать копию,
      чтобы объекты были действительно независимы.
   */
    //public interface Call<T> extends Cloneable {

    ////Аннотация @Query("name") String resourceName показывает Retrofit'у,
    //// что в качестве параметра запроса нужно поставить пару name=<Значение строки resourceName>.
    //Call<List <Rate> > getData(@Query("name") String resourceName, @Query("num") int count);
    //Observable<Rate> fetchDataCurrency();
    // Observable<List <Rate> > fetchDataCurrency();
    //Single<List <Rate> >  fetchRate();

    String BASE_URL = "http://ecocolor.in.ua/"; //test link template


    @Headers("Content-Type: application/json")
   // @GET("androidrest/apps/financial-quotations/module-quotes/data_out/{market}") //deprecated
    @GET("android-folder/public-json/market-rate/{market}")
    Call<List <Rate> > fetchData(@Path("market") String name);


}
