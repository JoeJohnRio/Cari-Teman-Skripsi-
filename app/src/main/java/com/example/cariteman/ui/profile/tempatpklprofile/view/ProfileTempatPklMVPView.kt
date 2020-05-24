package com.example.cariteman.ui.profile.tempatpklprofile.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface ProfileTempatPklMVPView : MVPView{
    fun setInfoProfil(response: TempatPklProfile)

    fun setUlasan(response: MutableList<UlasanTempatPklProfile>?)
}