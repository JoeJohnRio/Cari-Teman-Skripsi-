package com.example.cariteman.ui.loginadmin.presenter

import com.example.cariteman.data.model.Login
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.loginadmin.view.LoginAdminMVPView

interface LoginAdminMVPPresenter<V : LoginAdminMVPView> : MVPPresenter<V> {

    fun onLoginAdminButtonClicked(login: Login)
}