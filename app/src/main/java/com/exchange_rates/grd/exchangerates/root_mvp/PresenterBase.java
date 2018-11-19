package com.exchange_rates.grd.exchangerates.root_mvp;

//PresenterBase
/*
 Базовый класс, который будет наследоваться всеми презентерами.
  В нем реализованы общие методы по работе с View.
  В этом примере он достаточно прост.
  Но обычно здесь располагаются также механизмы для сбора RxJava подписок,
  которые завершаются в методе destroy.
 */

import android.util.Log;

import com.exchange_rates.grd.exchangerates.root_mvp.root.RootMvpPresenter;
import com.exchange_rates.grd.exchangerates.root_mvp.root.RootMvpView;

/**
Базовый класс, который будет наследоваться всеми презентерами.
 В нем реализованы общие методы по работе с View.
  В этом примере он достаточно прост.
  Но обычно здесь располагаются также механизмы для сбора RxJava подписок, которые завершаются в методе destroy.
 */
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

    //viewIsReady - сигнал презентеру о том, что View готово к работе. Презентер может начинать, например, загружать данные.
    // -задекларирован в интерфейсе
    // public abstract void viewIsReady();


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

    /**destroy - сигнал презентеру о том, что View завершает свою работу и будет закрыто.
     * Т.е. Здесь необходимо отписываться от всех моделей, завершать все текущие операции и т.п.
     */
//    @Override
//    public void destroy() {
//        Log.i(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());
//    }
}
