package com.example.cariteman.ui.dashboard.barudilihat.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatMVPView

interface BaruDilihatMVPPresenter <V : BaruDilihatMVPView> : MVPPresenter<V>{

    fun getHistoryProfilPkl(isRefresh: Boolean, pageNumber: Int)

    fun getHistoryProfilLomba(isRefresh: Boolean, pageNumber: Int)

    fun getHistoryProfilTempatPkl(isRefresh: Boolean, pageNumber: Int)

    fun toggleFavoriteFriend(idTarget: Int, isActive: Boolean)

    fun toggleFavoriteTempatPkl(idTarget: Int, isActive: Boolean)
}