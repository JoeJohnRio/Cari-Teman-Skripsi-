package com.example.cariteman.ui.profile.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface ProfileMVPView : MVPView{
    fun setInfoProfil(profilInfoOthers: ProfilInfoOthers)

    fun setPengalamanAndRekomendasi(rekomendasi: MutableList<Rekomendasi>?, rekomendasipengalamanLombaOrganisasiResponse: MutableList<PengalamanLombaOrganisasiResponse>)

    fun setFriendStatusView(profilInfoOthers: ProfilInfoOthers)

    fun handleShowKelompok(response: MutableList<RelationKelompok>)
}