package com.exchange_rates.grd.exchangerates.screens.screens_rate.all;

import android.util.Log;


import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.model_async.domain.interactor.InteractorAsyncRateImpl;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;
import com.exchange_rates.grd.exchangerates.root_mvp.PresenterBase;
import com.exchange_rates.grd.exchangerates.Rate;
import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.model_async.AsyncRateListenerOfInteractor;

import java.util.List;


public class AllRatesPresenterImpl extends PresenterBase<RateContract.View>
        implements RateContract.Presenter ,
        AsyncRateListenerOfInteractor
{



    //----------------------------------------------------------------------------------------------
   // private static final String LOG_TAG = AllRatesPresenterImpl.class.getName();
    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();
   // private static final String LOG_TAG = Thread.currentThread().getStackTrace()[1].getMethodName();
   //-----------------------------------------------------------------------------------------------


     private InteractorAsyncRateImpl interactor;
    //private InteractorSyncRateImpl interactor;


    @Override
    public void viewIsReady() {

        if(isViewAttached()){
            getView().showProgress();
        }else {
            getView().hideProgress();
            getView().showError(String.valueOf(R.string.display_message_1));
        }
    }

    @Override
    public void loadData(Market market) {
       // Log.v(LOG_TAG, ""+Thread.currentThread().getStackTrace()[2].getMethodName() );
        Log.v(LOG_TAG, ""+new Object(){}.getClass().getEnclosingMethod().getName() );
        interactor = new InteractorAsyncRateImpl(this,market);
    }
    //---------------------------------AsyncCallback------------------------------------------------
    /**
     * For showing error code
     * @param code
     */
    @Override
    public void onErrorCodeAsync(int code) {
        getView().hideProgress();
        getView().showError(String.valueOf(R.string.display_message_4));
    }

    /**
     * Receiving data
     *  @param data
     */
    @Override
    public void onSuccessAsync(List<Rate> data) {

        if(getView() == null){
            Log.d(LOG_TAG, "Uninitialized view!");
            onErrorMessageAsync(String.valueOf(R.string.display_message_4));
        }else {
            Log.d(LOG_TAG, "UI thread");
            getView().hideProgress();
            getView().showData(data);
            getView().showComplete();
        }
    }

    /**
     * For showing error message in main thread
     * @param message
     */
    @Override
    public void onErrorMessageAsync(String message) {
        //
        if(isViewAttached()) {
            getView().hideProgress();
            if (message.isEmpty()) {
                getView().showError(String.valueOf(R.string.display_message_2));
            } else {
                getView().showError(message);
            }
        }else {
            Log.d(LOG_TAG, "Uninitialized view! " +message);
        }
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public List<Rate> toSort(String string) {
        return interactor.searchFilterOfRate(string);
    }
    //----------------------------------------------------------------------------------------------

    @Override
    public void destroy() {
        Log.i(LOG_TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        interactor = null;
    }
}
