package com.example.cariteman.ui.verifikasiakun

import com.example.cariteman.ui.verifikasiakun.presenter.VerifikasiAkunMVPPresenter
import com.example.cariteman.ui.verifikasiakun.presenter.VerifikasiAkunPresenter
import com.example.cariteman.ui.verifikasiakun.view.VerifikasiAkunMVPView
import dagger.Module
import dagger.Provides

@Module
class VerifikasiAkunModule {
    @Provides
    internal fun provideVerifikasiAkunPresenter(mainPresenter: VerifikasiAkunPresenter<VerifikasiAkunMVPView>)
            : VerifikasiAkunMVPPresenter<VerifikasiAkunMVPView> = mainPresenter
}
