package com.example.cariteman.ui.profile.view

import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.ProfilInfoOthers
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.ui.base.MVPView

interface ProfileMVPView : MVPView{
    fun setInfoProfil(profilInfoOthers: ProfilInfoOthers)


}