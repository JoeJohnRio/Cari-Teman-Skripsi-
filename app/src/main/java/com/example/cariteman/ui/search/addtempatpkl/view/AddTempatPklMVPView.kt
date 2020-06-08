package com.example.cariteman.ui.search.filtersearch.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface AddTempatPklMVPView : MVPView{
    fun handleLokasiPkl(responses: MutableList<LokasiPklResponse>)

    fun handleAfterAddLokasiPkl()
}