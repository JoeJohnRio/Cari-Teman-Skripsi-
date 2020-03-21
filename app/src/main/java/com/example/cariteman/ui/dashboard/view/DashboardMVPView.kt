package com.example.cariteman.ui.dashboard.view

import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.ui.base.MVPView

interface DashboardMVPView : MVPView{
    fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>)
}