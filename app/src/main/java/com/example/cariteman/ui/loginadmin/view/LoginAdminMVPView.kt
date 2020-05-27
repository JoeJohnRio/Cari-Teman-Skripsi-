package com.example.cariteman.ui.loginadmin.view

import com.example.cariteman.ui.base.MVPView

interface LoginAdminMVPView : MVPView{
    fun goToDashboard()

    fun saveData(apiKey: String)
}