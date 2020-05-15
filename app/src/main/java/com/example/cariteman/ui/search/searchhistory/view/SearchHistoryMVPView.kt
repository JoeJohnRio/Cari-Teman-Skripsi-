package com.example.cariteman.ui.search.filtersearch.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface SearchHistoryMVPView : MVPView{

    fun showSearchHistory(searchHistory: MutableList<SearchHistory>)

    fun searchWithSearchHistory(searchHistory: SearchHistory)

    fun searchWithFilter(searchHistory: SearchHistory)
}