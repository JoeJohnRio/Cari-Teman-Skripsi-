package com.example.cariteman.ui.loginadmin.view

import com.example.cariteman.ui.base.MVPView

interface LoginAdminMVPView : MVPView{
    fun goToVerifikasiList()

    fun saveData(apiKey: String)
}