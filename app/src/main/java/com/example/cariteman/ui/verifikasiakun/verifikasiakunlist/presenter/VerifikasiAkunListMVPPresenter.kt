package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListMVPView

interface VerifikasiAkunListMVPPresenter<V: VerifikasiAkunListMVPView>: MVPPresenter<V>{

    fun showMahasiswa()

}