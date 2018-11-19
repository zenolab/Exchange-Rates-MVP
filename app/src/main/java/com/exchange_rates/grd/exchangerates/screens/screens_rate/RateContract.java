package com.exchange_rates.grd.exchangerates.screens.screens_rate;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.Rate;
import com.exchange_rates.grd.exchangerates.model_async.RepositoryCallbackListener;
import com.exchange_rates.grd.exchangerates.root_mvp.root.RootMvpPresenter;
import com.exchange_rates.grd.exchangerates.root_mvp.root.RootMvpView;
import com.exchange_rates.grd.exchangerates.root_mvp.root.root_model.RootMvpInteractor;
import com.exchange_rates.grd.exchangerates.root_mvp.root.root_model.RootMvpRepository;

import java.io.IOException;
import java.util.List;

/**
 * Only  inheritance
 * Not support override
 * For override need replace "RootMvpPresenter<View>"
 *
 * PresenterBase -  is abstract class !
 */
public  interface RateContract {


    interface View extends RootMvpView {

        void showData(List<Rate> data);
        void showError(String message);
        void showComplete();
        void showProgress();
        void hideProgress();

    }

    /** Final override of generic type!
     * Not support override  in further
     * abstract class PresenterBase
     */
    interface Presenter extends RootMvpPresenter<View> {

        void loadData(Market market);
        List<Rate> toSort(String string);
    }

    interface Interactor extends RootMvpInteractor {
        List<Rate> searchFilterOfRate(CharSequence charSequence);
    }

    interface Repository extends RootMvpRepository {
        List<Rate> getRateRepositoryRateSync(final Market market) throws IOException;
        void getRateRepositoryAsync(final RepositoryCallbackListener asyncListener, final Market market);

    }

}