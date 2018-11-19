package com.exchange_rates.grd.exchangerates.root_mvp;


import android.util.Log;

import com.exchange_rates.grd.exchangerates.root_mvp.root.RootMvpPresenter;
import com.exchange_rates.grd.exchangerates.root_mvp.root.RootMvpView;

public abstract class PresenterBase <T extends RootMvpView> implements RootMvpPresenter<T> {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private T view;

    public T getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    /**
     * attachView(V mvpView) - метод для передачи View презентеру. Т.е. View вызовет его и передаст туда себя
     */
    @Override
    public void attachView(T rootMvpView) {

        this.view = rootMvpView;
    }

    /**
     * detachView - презентер должен отпустить View. Вызывается, например, при повороте экрана,
     * когда уничтожается старый экземпляр Activity, или при закрытии Activity.
     * Презентер должен обнулить ссылку на Activity.
    */
    @Override
    public void detachView() {
        Log.w(LOG_TAG, Thread.currentThread().getStackTrace()[2].getMethodName() );
        view = null;
    }

}
