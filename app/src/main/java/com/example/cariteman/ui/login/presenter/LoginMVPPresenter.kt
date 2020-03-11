package com.example.cariteman.ui.login.presenter

import com.example.cariteman.data.model.Login
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.login.view.LoginMVPView

interface LoginMVPPresenter <V : LoginMVPView> : MVPPresenter<V> {

    fun onLoginBtnClicked(login: Login)
}