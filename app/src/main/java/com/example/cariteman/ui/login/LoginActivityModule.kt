package com.example.cariteman.ui.login

import com.example.cariteman.ui.login.presenter.LoginMVPPresenter
import com.example.cariteman.ui.login.presenter.LoginPresenter
import com.example.cariteman.ui.login.view.LoginMVPView
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    internal fun providesLoginActivity(mainPresenter: LoginPresenter<LoginMVPView>)
            : LoginMVPPresenter<LoginMVPView> = mainPresenter
}