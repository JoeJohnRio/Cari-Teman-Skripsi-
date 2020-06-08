package com.example.cariteman.ui.dashboard.barudilihat.view

import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RekomendasiResponse
import com.example.cariteman.ui.base.MVPView

interface RekomendasiMVPView : MVPView{

    fun populateRekomendasiProfile(responses: MutableList<RekomendasiResponse>)
}