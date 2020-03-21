package com.example.cariteman.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.example.cariteman.R
import com.example.cariteman.ui.base.MVPView
import com.example.cariteman.util.CommonUtil
import com.example.cariteman.util.Utils
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), MVPView,
    BaseFragment.CallBack {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
    }

    override fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    fun getKey(): String {
        val sharedPreferences = applicationContext?.getSharedPreferences(
            Utils.SHARED_PREFS,
            AppCompatActivity.MODE_PRIVATE
        )
        return sharedPreferences.toString()
    }

    private fun performDI() = AndroidInjection.inject(this)

}