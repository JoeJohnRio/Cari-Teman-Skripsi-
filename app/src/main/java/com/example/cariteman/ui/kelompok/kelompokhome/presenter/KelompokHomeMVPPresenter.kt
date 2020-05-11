package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView

interface KelompokHomeMVPPresenter<V: KelompokHomeMVPView>: MVPPresenter<V>{

    fun showKelompok()

}