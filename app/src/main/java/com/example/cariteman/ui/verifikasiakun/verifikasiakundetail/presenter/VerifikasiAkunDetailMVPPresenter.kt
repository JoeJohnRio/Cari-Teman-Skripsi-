package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.presenter

import com.example.cariteman.data.model.MahasiswaNotVerifiedListResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunDetailMVPView
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListMVPView

interface VerifikasiAkunDetailMVPPresenter<V: VerifikasiAkunDetailMVPView>: MVPPresenter<V>{

    fun getMahasiswaDetail(response: MahasiswaNotVerifiedListResponse)

    fun confirmMahasiswa(response: MahasiswaNotVerifiedListResponse)
}