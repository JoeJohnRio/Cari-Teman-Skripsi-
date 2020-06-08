package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.data.model.TempatPklResponse
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.search.filtersearch.view.AddTempatPklMVPView
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchMVPView
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryMVPView

interface AddTempatPklMVPPresenter<V: AddTempatPklMVPView>: MVPPresenter<V>{

    fun getLokasiPkl()

    fun addTempatPkl(tempatPkl: TempatPklResponse)
}