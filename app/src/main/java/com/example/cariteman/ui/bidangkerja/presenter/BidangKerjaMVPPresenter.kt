package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView

interface BidangKerjaMVPPresenter<V: BidangKerjaMVPView>: MVPPresenter<V>{
    fun getBidangKerjaSearch(namaBidangKerja: String)

    fun makeNewBidangKerja(namaBidangKerja: String)
}