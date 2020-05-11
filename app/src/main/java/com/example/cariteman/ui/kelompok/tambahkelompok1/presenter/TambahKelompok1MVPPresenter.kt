package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.Kelompok
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.TambahKelompok1MVPView

interface TambahKelompok1MVPPresenter<V: TambahKelompok1MVPView>: MVPPresenter<V>{

    fun showFriendWithNameOnly()

    fun addFriendToKelompok(kelompok: Kelompok)

    fun showFriendNotAddedYet(idKelompok: Int)
}