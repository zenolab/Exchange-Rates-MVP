package com.exchange_rates.grd.exchangerates.screens.screens_rate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.exchange_rates.grd.exchangerates.R

class AttentionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attention_display)
    }
    override fun onBackPressed() {
        super.onBackPressed();
        finishAffinity(); //min api16
        // System.exit(0); //restart app
    }
}