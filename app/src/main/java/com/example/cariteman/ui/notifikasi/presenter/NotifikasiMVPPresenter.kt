package com.example.cariteman.ui.notifikasi.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.notifikasi.view.NotifikasiMVPView

interface NotifikasiMVPPresenter <V : NotifikasiMVPView> : MVPPresenter<V> {
    fun showNotifikasi()
}