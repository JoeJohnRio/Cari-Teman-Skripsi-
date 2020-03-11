package com.example.cariteman.ui.register.view

import androidx.fragment.app.Fragment
import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView


interface RegisterMVPView : MVPView {

    fun openRegisterFragment()

    fun showFakultas(responses: ArrayList<Fakultas>)

    fun showProgramStudi(responses: ArrayList<ProgramStudi>)

    fun showKeminatan(responses: ArrayList<Keminatan>)

}