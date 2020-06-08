package com.example.cariteman.ui.dashboard.barudilihat

import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatMVPPresenter
import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatPresenter
import com.example.cariteman.ui.dashboard.barudilihat.presenter.RekomendasiMVPPresenter
import com.example.cariteman.ui.dashboard.barudilihat.presenter.RekomendasiPresenter
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatMVPView
import com.example.cariteman.ui.dashboard.barudilihat.view.RekomendasiMVPView
import dagger.Module
import dagger.Provides

@Module
class ListRekomendasiModule {
    @Provides
    internal fun provideListRekomendasiPresenter(mainPresenter: RekomendasiPresenter<RekomendasiMVPView>)
            : RekomendasiMVPPresenter<RekomendasiMVPView> = mainPresenter
}