package com.exchange_rates.grd.exchangerates.model_async.domain.interactor;

import android.util.Log;

import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.model_async.AsyncRateListenerOfInteractor;
import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.Rate;


import com.exchange_rates.grd.exchangerates.model_async.RepositoryCallbackListener;
import com.exchange_rates.grd.exchangerates.model_async.repository.RepositoryRate;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;

import java.util.ArrayList;
import java.util.List;


public class InteractorAsyncRateImpl implements RateContract.Interactor,RepositoryCallbackListener
{

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private AsyncRateListenerOfInteractor presenterCallbackAcceptor;
    private static List<Rate>  rateList;
    public RepositoryRate repositoryRate = new RepositoryRate();
    private  List<Rate>  ratesListSorted = new ArrayList<>();;


         /** Constructor
          * @param presenterCallbackAcceptor - callback listener from presenter
          * @param market
          */
    public InteractorAsyncRateImpl(AsyncRateListenerOfInteractor presenterCallbackAcceptor, final Market market) {
        this.presenterCallbackAcceptor = presenterCallbackAcceptor;
        repositoryRate.getRateRepositoryAsync(this,market);
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
    @Override
    public void onErrorАnswer(String string) {
        this.presenterCallbackAcceptor.onErrorMessageAsync(string);
        this.presenterCallbackAcceptor.onErrorMessageAsync(String.valueOf(R.string.display_message_3));
    }
    @Override
    public void onErrorCodeAnswer(int code) {
         this.presenterCallbackAcceptor.onErrorCodeAsync(code);
    }
    @Override
    public void onSuccessAnswer(List<Rate> data) {
         this.rateList = data;
         this.presenterCallbackAcceptor.onSuccessAsync(data);
    }
    //----------------------------------------------------------------------------------------------
}


