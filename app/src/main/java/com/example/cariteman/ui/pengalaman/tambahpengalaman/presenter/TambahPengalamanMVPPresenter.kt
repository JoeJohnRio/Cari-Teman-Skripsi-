package com.example.cariteman.ui.pengalaman.tambahpengalaman.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanMVPView

interface TambahPengalamanMVPPresenter <V: TambahPengalamanMVPView>: MVPPresenter<V>{

    fun modifyPengalamanOrganisasi(response: PengalamanLombaOrganisasiResponse)

    fun modifyPengalamanLomba(response: PengalamanLombaOrganisasiResponse)


}