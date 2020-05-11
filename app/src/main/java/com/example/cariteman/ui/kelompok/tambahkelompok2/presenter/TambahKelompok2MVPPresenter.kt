package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.Kelompok
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.TambahKelompok2MVPView

interface TambahKelompok2MVPPresenter<V: TambahKelompok2MVPView>: MVPPresenter<V>{
    fun makeKelompok(kelompok: Kelompok)
}