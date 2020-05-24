package com.example.cariteman.ui.register.presenter

import com.example.cariteman.data.model.Mahasiswa
import com.example.cariteman.data.model.MahasiswaResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
//import com.example.cariteman.ui.register.interactor.RegisterMVPInteractor
import com.example.cariteman.ui.register.view.RegisterMVPView

interface RegisterMVPPresenter<V : RegisterMVPView> : MVPPresenter<V> {

    fun onNextRegisterClick(nim: String, email: String) : Unit?

    fun getFakultasResponse()

    fun getProgramStudiResponse(id: Int)

    fun getKeminatanResponse(id: Int)

    fun sendMahasiswaData(mahasiswa: MahasiswaResponse)

}