package com.example.cariteman.ui.dashboard.barudilihat.view

import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.ui.base.MVPView

interface BaruDilihatMVPView : MVPView{

    fun populateBaruDilihatProfil(responses: List<MahasiswaHistoryDashboardResponse>)

    fun setLastPageLimiter(lastPage: Int)
}