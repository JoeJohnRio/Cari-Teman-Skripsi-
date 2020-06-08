package com.example.cariteman.ui.pengaturan

import com.example.cariteman.ui.pengaturan.presenter.PengaturanMVPPresenter
import com.example.cariteman.ui.pengaturan.presenter.PengaturanPresenter
import com.example.cariteman.ui.register.view.PengaturanMVPView
import dagger.Module
import dagger.Provides

@Module
class PengaturanActivityModule {

    @Provides
    internal fun provideMainPresenter(mainPresenter: PengaturanPresenter<PengaturanMVPView>)
            : PengaturanMVPPresenter<PengaturanMVPView> = mainPresenter
}