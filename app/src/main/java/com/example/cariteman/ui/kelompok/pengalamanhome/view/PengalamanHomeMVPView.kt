package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface PengalamanHomeMVPView : MVPView{
    fun setPengalamanAdapter(pengalaman: MutableList<PengalamanLombaOrganisasiResponse>)

    fun getModifyPengalamanLombaFragment(pengalaman: PengalamanLombaOrganisasiResponse)

    fun getModifyPengalamanOrganisasiFragment(pengalaman: PengalamanLombaOrganisasiResponse)
}