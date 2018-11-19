package com.exchange_rates.grd.exchangerates.screens

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.View
import com.exchange_rates.grd.exchangerates.R

class InfoDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!)
                .setTitle(activity!!.getString(R.string.informer_app))
                .setMessage(activity!!.getString(R.string.informer_txt) )
                .setNegativeButton(android.R.string.cancel, null)
                .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState) }
}


