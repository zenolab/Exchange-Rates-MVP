package com.exchange_rates.grd.exchangerates.screens.screens_rate.foreign_currency;

import android.util.Log;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.model_sync.domain.pojo.Rate;

import com.exchange_rates.grd.exchangerates.model_sync.SynchronousRateListener;
import com.exchange_rates.grd.exchangerates.model_sync.domain.interactor.InteractorSyncRateImpl;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;
import com.exchange_rates.grd.exchangerates.root_mvp.PresenterBase;

import java.util.List;

public class CurrencyPresenterImp extends PresenterBase<RateContract.View>
        implements
        RateContract.Presenter,
        SynchronousRateListener
{

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private InteractorSyncRateImpl interactor;

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
        interactor = null;
    }

    @Override
    public void loadData(Market market) {
        Log.v(LOG_TAG, ""+new Object(){}.getClass().getEnclosingMethod().getName() );
        interactor =  new InteractorSyncRateImpl(this,Market.Currency);
    }

    @Override
    public List<Rate> toSort(String string) {
        return interactor.searchFilterOfRate(string);
    }

    @Override
    public void onError() {
        getView().hideProgress();
        getView().showError(String.valueOf(R.string.display_message_2));
    }

    @Override
    public void onSuccess(List<Rate> rateList ) {
        getView().hideProgress();
        getView().showData(rateList);
        getView().showComplete();
    }
}
