package com.example.cariteman.ui.search.filtersearch.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface FrontProfileMVPView : MVPView{

    fun handleSearchMahasiswa(mahasiswaResponse: SearchFilter)

    fun handleSearchTempatPkl(tempatPklResponse: SearchFilter)
}