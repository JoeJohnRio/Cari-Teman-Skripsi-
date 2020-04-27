package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.dashboard.presenter.PengalamanMVPPresenter
import com.example.cariteman.ui.dashboard.presenter.PengalamanPresenter
import com.example.cariteman.ui.pengalaman.view.PengalamanMVPView
import dagger.Module
import dagger.Provides

@Module
class PengalamanModule {
    @Provides
    internal fun providePengalamanPresenter(mainPresenter: PengalamanPresenter<PengalamanMVPView>)
            : PengalamanMVPPresenter<PengalamanMVPView> = mainPresenter
}