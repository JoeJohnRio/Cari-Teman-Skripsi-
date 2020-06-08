package com.example.cariteman.ui.firstpage

import com.example.cariteman.ui.firstpage.presenter.FirstPageMVPPresenter
import com.example.cariteman.ui.firstpage.presenter.FirstPagePresenter
import com.example.cariteman.ui.firstpage.view.FirstPageMVPView
import com.example.cariteman.ui.login.presenter.LoginMVPPresenter
import com.example.cariteman.ui.login.presenter.LoginPresenter
import com.example.cariteman.ui.login.view.LoginMVPView
import dagger.Module
import dagger.Provides

@Module
class FirstPageActivityModule {
    @Provides
    internal fun providesFirstPageActivity(mainPresenter: FirstPagePresenter<FirstPageMVPView>)
            : FirstPageMVPPresenter<FirstPageMVPView> = mainPresenter
}