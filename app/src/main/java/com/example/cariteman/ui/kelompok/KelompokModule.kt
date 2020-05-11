package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.dashboard.presenter.KelompokMVPPresenter
import com.example.cariteman.ui.dashboard.presenter.KelompokPresenter
import com.example.cariteman.ui.dashboard.presenter.PengalamanMVPPresenter
import com.example.cariteman.ui.dashboard.presenter.PengalamanPresenter
import com.example.cariteman.ui.pengalaman.view.KelompokMVPView
import com.example.cariteman.ui.pengalaman.view.PengalamanMVPView
import dagger.Module
import dagger.Provides

@Module
class KelompokModule {
    @Provides
    internal fun provideKelompokPresenter(mainPresenter: KelompokPresenter<KelompokMVPView>)
            : KelompokMVPPresenter<KelompokMVPView> = mainPresenter
}