package com.exchange_rates.grd.exchangerates.model_sync.domain.interactor;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.model_sync.SynchronousRateListener;
import com.exchange_rates.grd.exchangerates.Rate;
import com.exchange_rates.grd.exchangerates.model_sync.repository.RepositoryRateSync;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Interactor - Бизнес логика  - обработка данных
public class InteractorSyncRateImpl implements RateContract.Interactor {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private  List<Rate>  rateList;
    private  List<Rate>  ratesListSorted = new ArrayList<>();;

    //---------------------------------------------------------------
    public RepositoryRateSync repositoryRateSync = new RepositoryRateSync();

   //------------------Constructor-------------------------------------
    /**
     * Constructor
     */
    public InteractorSyncRateImpl(final SynchronousRateListener listener,final Market market ) {

        getDateDoInBackground( listener,market);
        //-------------------------------------------
        //  RateTask rateTask = new RateTask(listener);
       //  rateTask.execute(market);
    }
//----------------------------------Handler --------------------------------------------------
    /** 
     * Retrieving synchronous http data from the repository
     * 
     * @param market Business data.
     * @param listener Callback  for interaction with presenter
     */
   private void getDateDoInBackground(final SynchronousRateListener listener,final Market market ) {
       // final параметры невозможно переназначить в теле метода!!!
        Thread repositoryThread = new Thread(new Runnable(){
            @Override
            public void run(){
                //code to do the HTTP request
                try{
                    toMainThread(repositoryRateSync.getRateRepositoryRateSync(market),listener,true);
                    return;
                }catch (IOException e) {
                    Log.d(LOG_TAG, "Error Connection");
                    toMainThread(null,listener,false);
                    e.printStackTrace();
                    return;
                }
            }
        });
        repositoryThread.start();

    }
    /**
     * Method for transferring data from repository to presenter callbacks
     * Applying communication through Handler ,like a bridge for output state to the main thread
     * Looper.getMainLooper() - used to access the main thread of execution (UI thread)
     *
     * @param rates  Business data.
     * @param listener Callback  for interaction with presenter
     * @param connection Successful/unsuccessful connection
     */
    private void toMainThread(final List<Rate> rates, final SynchronousRateListener listener, boolean connection) {
        this.rateList = rates;
        //Looper.getMainLooper() - Полезно, если вы хотите выпустить некоторый код в основном потоке из фонового потока.
        new Handler(Looper.getMainLooper()).post(new RunnableRate(rates) {
            @Override
            public void run() {
                Log.d(LOG_TAG, "UI thread");
                if(connection){
                     Log.d(LOG_TAG, " Connection is success " );
                     listener.onSuccess(rates);
                }else{
                    listener.onError();
                }
            }
        });
    }
    //---------------------------------------------------------------
    //   AsyncTask
//            Минусы:
//     1. Memory leak при перевороте экрана — старая activity
//       не уничтожиться пока задача не выполниться,
//       и результат придет в старую activity, и, скорее всего, вы словите exception.
//     2. Нет обработки ошибок.
//     3. Долгие операции будут прерваны
//       как только система решит уничтожить ваше приложение (если вы его свернули например).

    //--------------------------AsyncTask-------------------------------------

    /**
     *
     *
     *
     *  AsyncTask<Void, Void, List<Rate>>
     * 1) Тип входных данных. Это данные, которые пойдут на вход AsyncTask
     * 2) Тип промежуточных данных. Данные, которые используются для вывода промежуточных результатов
     * 3) Тип возвращаемых данных. То, что вернет AsyncTask после работы.
     */

    class RateTask extends AsyncTask<Market, Void, List<Rate>>  {

        //Resolve Memory leaks
        // http://www.ohandroid.com/android-asynctask-19.html
        // https://habr.com/post/124484/
        // http://developer.alexanderklimov.ru/android/debug/memoryleak.php
        //----------------------------
        private List<Rate> rateData = null;
        SynchronousRateListener listener;

        RateTask(final SynchronousRateListener listener){
            this.listener = listener;
        }
        //-----------------------------
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(LOG_TAG, "Begin");
        }

        @Override
        protected List<Rate> doInBackground(Market... params) {
            try {
                rateData = repositoryRateSync.getRateRepositoryRateSync(params[0]);
                rateList = rateData;
                Log.d(LOG_TAG, "End.doInBackground  Result = " + rateData);
            } catch (IOException e) {
                rateData =null;
                //SynchronousRateListener listener
                listener.onError();
                e.printStackTrace();
            }
            return rateData;
        }

        @Override
        protected void onPostExecute(List<Rate> result) {
            super.onPostExecute(result);
            Log.d(LOG_TAG, "onPostExecute Result = " + result);
            rateData = result;
            //Так презентентер не будет проинициализирован ,потому-что getView инициализируется в Activity
            // AllRatesPresenterImpl presenter = new AllRatesPresenterImpl();
            listener.onSuccess(rateData);
            Log.d(LOG_TAG, "End. Result rateData = " + result);

        }

    }
    //----------------------------------------------------------------------------------------------
    @Override
    public List<Rate> searchFilterOfRate(CharSequence charSequence) {
        String charString = charSequence.toString();
        if (charString.isEmpty()) {
            return ratesListSorted = rateList;
        } else {
            List<Rate> filteredList = new ArrayList<>();
            for (Rate row : rateList) {
                Log.e(LOG_TAG, "row "+row.getSymbol().toLowerCase().contains(charString.toLowerCase()) );
                if (row.getSymbol().toLowerCase().contains(charString.toLowerCase()) ) {
                    filteredList.add(row);
                    Log.e(LOG_TAG, "filteredList "+filteredList.size() );
                }
            }
            return   ratesListSorted = filteredList;
        }
    }
    //----------------------------------------------------------------------------------------------


}


