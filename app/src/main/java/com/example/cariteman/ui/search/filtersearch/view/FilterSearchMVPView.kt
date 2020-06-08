package com.example.cariteman.ui.search.filtersearch.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface FilterSearchMVPView : MVPView{
    fun showFakultas(responses: ArrayList<Fakultas>)

    fun showProgramStudi(responses: ArrayList<ProgramStudi>)

    fun showKeminatan(responses: ArrayList<Keminatan>)

    fun handleLokasiPkl(responses: MutableList<LokasiPklResponse>)

    fun backstack()
}