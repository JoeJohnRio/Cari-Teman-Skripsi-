package com.example.cariteman.ui.dashboard.barudilihat

import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatMVPPresenter
import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatPresenter
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatMVPView
import dagger.Module
import dagger.Provides

@Module
class BaruDilihatModule {
    @Provides
    internal fun provideBaruDilihatPresenter(mainPresenter: BaruDilihatPresenter<BaruDilihatMVPView>)
            : BaruDilihatMVPPresenter<BaruDilihatMVPView> = mainPresenter
}