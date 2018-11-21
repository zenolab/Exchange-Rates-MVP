package com.exchange_rates.grd.exchangerates.screens.screens_rate.foreign_currency;

import android.util.Log;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.model.domain.interactor.InteractorRateImpl;
import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;
import com.exchange_rates.grd.exchangerates.model.InteractorListener;

import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;
import com.exchange_rates.grd.exchangerates.root_mvp.PresenterBase;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class CurrencyPresenterImp extends PresenterBase<RateContract.View>
        implements
        RateContract.Presenter,
        InteractorListener {


    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    InteractorRateImpl interactor;
    private Disposable disposable;


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
    public void destroy() {
        Log.i(LOG_TAG, Thread.currentThread().getStackTrace()[2].getMethodName());

        if (this.disposable  != null && !this.disposable .isDisposed()) {
            this.disposable.dispose();
        }
    }
    @Override
    public void loadData(Market market) {
        Log.v(LOG_TAG, ""+new Object(){}.getClass().getEnclosingMethod().getName() );
        interactor = new InteractorRateImpl(this,market);
    }
    @Override
    public List<Rate> toSort(String string) {
        return interactor.searchFilterOfRate(string);
    }
    //-----------------------------Interactor listener ---------------------------------------------
    /**
     * @param disposable
     */
    @Override
    public void modelState(Disposable disposable) {
                this.disposable = disposable;
    }
    /**
     * @param data
     */
    @Override
    public void onSuccess(List<Rate> data) {

        if(getView() == null){
            Log.d(LOG_TAG, "Uninitialized view!");
            onErrorMessage(String.valueOf(R.string.display_message_4));
        }else {
            if(data!=null){
                Log.d(LOG_TAG, "UI thread");
                getView().hideProgress();
                getView().showData(data);
                getView().showComplete();
            }else{
                Log.d(LOG_TAG, "SERVER IS NOT AVAILABLE !");
                getView().showAttention();
            }
        }
    }
    /**
     * For showing error message in main thread from repository api
     *
     * @param message
     */
    @Override
    public void onErrorMessage(String message) {
        getView().hideProgress();
        getView().showError(String.valueOf(R.string.display_message_2));
    }
    //----------------------------------------------------------------------------------------------
}
