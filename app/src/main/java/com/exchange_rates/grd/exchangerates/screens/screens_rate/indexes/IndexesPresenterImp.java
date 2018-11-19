package com.exchange_rates.grd.exchangerates.screens.screens_rate.indexes;

import android.util.Log;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.Rate;
import com.exchange_rates.grd.exchangerates.model_async.AsyncRateListenerOfInteractor;
import com.exchange_rates.grd.exchangerates.model_async.domain.interactor.InteractorAsyncRateImpl;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;
import com.exchange_rates.grd.exchangerates.root_mvp.PresenterBase;

import java.util.List;

public class IndexesPresenterImp extends PresenterBase<RateContract.View>
        implements RateContract.Presenter ,
        AsyncRateListenerOfInteractor {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private InteractorAsyncRateImpl interactor;

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

        Log.e(LOG_TAG, "--------------M Presenter V :loadData() " );

        //Бесконечно долго крутиться  progress bar если нету запроса к Repository
        //-----------------Synchronous case---------
       // interactor= new InteractorSyncRateImpl(this,Market.AllData);

        //--------------Async-------------
        interactor = new InteractorAsyncRateImpl(this, market);
        //usual
        // InteractorRateImpl interactorRateImpl = new InteractorRateImpl(this);
        // InteractorRateImpl interactorRateImpl = new InteractorRateImpl();

    }

    @Override
    public List<Rate> toSort(String string) {
        return null;
    }

    //---------------Asynchronous Interactor Listener callback--------------------------------------
    /**
     * For showing error code
     *
     * @param code
     */
    @Override
    public void onErrorCodeAsync(int code) {
        getView().hideProgress();
        getView().showError(String.valueOf(R.string.display_message_4));
    }

    /**
     * The provision  asynchronous  data from the repository to interactor/presenter
     *
     * @param data
     */
    @Override
    public void onSuccessAsync(List<Rate> data) {
        getView().hideProgress();
        getView().showData(data);
        getView().showComplete();
    }

    /**
     * For showing error message in main thread from repository api
     *
     * @param message
     */
    @Override
    public void onErrorMessageAsync(String message) {
        getView().hideProgress();
        getView().showError(String.valueOf(R.string.display_message_2));
    }
    //----------------------------------------------------------------------------------------------

    @Override
    public void destroy() {
        Log.i(LOG_TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
        interactor = null;
    }
}