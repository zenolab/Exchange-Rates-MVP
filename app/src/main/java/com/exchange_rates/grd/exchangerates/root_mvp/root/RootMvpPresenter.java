package com.exchange_rates.grd.exchangerates.root_mvp.root;

public interface RootMvpPresenter<V extends RootMvpView> {

    /**
     *
     * @param rootMvpView  - placeholder for view
     */
    void attachView(V rootMvpView);

    /**
     *  A signal to the presenter that view is ready
     *  The presenter may begin,for example ,to load data.
     */
    void viewIsReady();

    /**
     * Releasing the view from the old data after changing the display configuration
     */
    void detachView();

    /**
     * Releasing resource of model
     */
    void destroy();

}
