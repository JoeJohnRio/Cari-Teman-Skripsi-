package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.notifikasi.presenter.NotifikasiMVPPresenter
import com.example.cariteman.ui.notifikasi.presenter.NotifikasiPresenter
import com.example.cariteman.ui.notifikasi.view.NotifikasiMVPView
import dagger.Module
import dagger.Provides

@Module
class NotifikasiModule {
    @Provides
    internal fun provideNotifikasiPresenter(mainPresenter: NotifikasiPresenter<NotifikasiMVPView>)
            : NotifikasiMVPPresenter<NotifikasiMVPView> = mainPresenter
}