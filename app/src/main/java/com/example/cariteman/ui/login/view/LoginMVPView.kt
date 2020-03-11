package com.example.cariteman.ui.login.view

import com.example.cariteman.ui.base.MVPView

interface LoginMVPView : MVPView{
    fun goToDashboard()

    fun saveData(apiKey: String)
}