package com.exchange_rates.grd.exchangerates.screens.screens_rate.crypto_currency;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.exchange_rates.grd.exchangerates.Market;
import com.exchange_rates.grd.exchangerates.R;
import com.exchange_rates.grd.exchangerates.Rate;
import com.exchange_rates.grd.exchangerates.adapter.CustomAdapter;
import com.exchange_rates.grd.exchangerates.screens.AttentionActivity;
import com.exchange_rates.grd.exchangerates.screens.screens_rate.RateContract;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CryptoFragmentView extends Fragment implements RateContract.View  {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();


    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ProgressDialog progressDialog;


    private CryptoCurrencyPresenterImpl presenter;


    public CryptoFragmentView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_crypto, container, false);
        progressDialog = new ProgressDialog(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        return rootView;
    }


    private void initPresenter() {
        presenter = new CryptoCurrencyPresenterImpl();
        presenter.attachView(this);
        presenter.viewIsReady();
        presenter.loadData(Market.Crypto);
    }

    @Override
    public void showData(List<Rate> data) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         customAdapter = new CustomAdapter(getActivity(), data);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void showError(String labelResId) {
        String message = getActivity().getString(R.string.display_message_2);
        Toast.makeText(getActivity(), message+labelResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        String message = getActivity().getString(R.string.display_message_complete);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgress() {
        progressDialog.setMessage(getActivity().getString(R.string.display_message_loading));
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showAttention() {
        Intent intent = new Intent(getActivity(), AttentionActivity.class);
        startActivity(intent);
    }


    //----------------------------------------------------------------------------------------------
    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());
        initPresenter();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());
        presenter.detachView();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());
        presenter.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(LOG_TAG, " "+Thread.currentThread().getStackTrace()[2].getMethodName());

    }

}
