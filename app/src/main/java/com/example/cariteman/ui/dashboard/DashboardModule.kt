package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.dashboard.presenter.DashboardMVPPresenter
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView
import dagger.Module
import dagger.Provides

@Module
class DashboardModule{

    @Provides
    internal fun provideMainPresenter(mainPresenter: DashboardPresenter<DashboardMVPView>)
            : DashboardMVPPresenter<DashboardMVPView> = mainPresenter
}