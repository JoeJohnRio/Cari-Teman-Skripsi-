package com.example.cariteman.ui.dashboard.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView

interface DashboardMVPPresenter <V : DashboardMVPView> : MVPPresenter<V> {

    fun getHistoryDashboardLombaResponse()

    fun getHistoryDashboardPklResponse()

    fun getHistoryDashboardTempatPklResponse()

    fun getFavoriteFriendLombaResponse(isRefresh: Boolean, pageNumber: Int)

    fun getFavoriteFriendPklResponse(isRefresh: Boolean, pageNumber: Int)

    fun getFavoriteTempatPklResponse(isRefresh: Boolean, pageNumber: Int)

    fun toggleFavoriteFriend(idTarget: Int, isActive: Boolean)

    fun toggleFavoriteTempatPkl(idTarget: Int, isActive: Boolean)
}