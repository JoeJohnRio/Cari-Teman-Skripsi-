package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.SkillHobiMVPView

interface SkillHobiMVPPresenter<V: SkillHobiMVPView>: MVPPresenter<V>{
    fun getSkillHobiSearch(namaSkillHobi: String)

    fun makeNewSkillHobi(namaSkillHobi: String)
}