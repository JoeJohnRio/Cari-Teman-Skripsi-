package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface SkillHobiMVPView : MVPView{
    fun showBidangKerja(listOfBidangKerja: MutableList<SkillHobi>)

    fun popBackStackWithBidangKerja(skillHobi: SkillHobi)
}