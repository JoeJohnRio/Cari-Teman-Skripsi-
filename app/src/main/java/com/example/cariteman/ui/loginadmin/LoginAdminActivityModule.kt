package com.example.cariteman.ui.login

import com.example.cariteman.ui.login.presenter.LoginMVPPresenter
import com.example.cariteman.ui.login.presenter.LoginPresenter
import com.example.cariteman.ui.login.view.LoginMVPView
import com.example.cariteman.ui.loginadmin.presenter.LoginAdminMVPPresenter
import com.example.cariteman.ui.loginadmin.presenter.LoginAdminPresenter
import com.example.cariteman.ui.loginadmin.view.LoginAdminMVPView
import dagger.Module
import dagger.Provides

@Module
class LoginAdminActivityModule {
    @Provides
    internal fun providesLoginAdminActivity(mainPresenter: LoginAdminPresenter<LoginAdminMVPView>)
            : LoginAdminMVPPresenter<LoginAdminMVPView> = mainPresenter
}