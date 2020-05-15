package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchMVPView

interface FilterSearchMVPPresenter<V: FilterSearchMVPView>: MVPPresenter<V>{

    fun getFakultasResponse()

    fun getProgramStudiResponse(id: Int)

    fun getKeminatanResponse(id: Int)
}