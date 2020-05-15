package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchMVPView
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryMVPView

interface SearchHistoryMVPPresenter<V: SearchHistoryMVPView>: MVPPresenter<V>{

    fun addSearchHistory(searchHistory: SearchHistory)

    fun showSearchHistory()
}