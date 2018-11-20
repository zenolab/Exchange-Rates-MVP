package com.exchange_rates.grd.exchangerates.model.domain.interactor;

import android.util.Log;

import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.model.InteractorListener;
import com.exchange_rates.grd.exchangerates.Market;


import com.exchange_rates.grd.exchangerates.model.RepositoryListener;
import com.exchange_rates.grd.exchangerates.model.domain.interactor.pojo.Rate;
import com.exchange_rates.grd.exchangerates.model.repository.RepositoryRate;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


public class InteractorRateImpl implements RateContract.Interactor,RepositoryListener
{

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private InteractorListener presenterCallbackAcceptor;
    private static List<Rate>  rateList;
    public RepositoryRate repositoryRate = new RepositoryRate();
    private  List<Rate>  ratesListSorted = new ArrayList<>();



         /** Constructor
          * @param presenterCallbackAcceptor - callback listener from presenter
          * @param market
          */
    public InteractorRateImpl(InteractorListener presenterCallbackAcceptor, final Market market) {
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
        this.presenterCallbackAcceptor.onErrorMessage(string);
        this.presenterCallbackAcceptor.onErrorMessage(String.valueOf(R.string.display_message_3));
    }
    @Override
    public void subscription(Disposable disposable) {
        this.presenterCallbackAcceptor.modelState(disposable);
    }
    @Override
    public void onSuccessAnswer(List<Rate> data) {
         this.rateList = data;
         this.presenterCallbackAcceptor.onSuccess(data);
    }
    //----------------------------------------------------------------------------------------------
}


