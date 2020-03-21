package com.example.cariteman.ui.register

import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import com.example.cariteman.ui.register.presenter.RegisterPresenter
import com.example.cariteman.ui.register.view.RegisterMVPView
import dagger.Module
import dagger.Provides

@Module
class RegisterActivityModule {

    @Provides
    internal fun provideMainPresenter(mainPresenter: RegisterPresenter<RegisterMVPView>)
            : RegisterMVPPresenter<RegisterMVPView> = mainPresenter
}