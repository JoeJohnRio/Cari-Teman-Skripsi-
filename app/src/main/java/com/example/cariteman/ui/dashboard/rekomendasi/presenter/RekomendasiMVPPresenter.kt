package com.example.cariteman.ui.dashboard.barudilihat.presenter

import com.example.cariteman.data.model.RekomendasiResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatMVPView
import com.example.cariteman.ui.dashboard.barudilihat.view.RekomendasiMVPView

interface RekomendasiMVPPresenter <V : RekomendasiMVPView> : MVPPresenter<V>{

    fun getHistoryProfilPkl(rekomendasiResponse: RekomendasiResponse)

    fun toggleFavoriteFriend(idTarget: Int, isActive: Boolean)

    fun toggleFavoriteTempatPkl(idTarget: Int, isActive: Boolean)
}