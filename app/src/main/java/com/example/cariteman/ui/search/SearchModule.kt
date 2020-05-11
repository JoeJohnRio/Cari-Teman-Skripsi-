package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.dashboard.presenter.SearchMVPPresenter
import com.example.cariteman.ui.dashboard.presenter.SearchPresenter
import com.example.cariteman.ui.pengalaman.view.SearchMVPView
import dagger.Module
import dagger.Provides

@Module
class SearchModule {
    @Provides
    internal fun provideSearchPresenter(mainPresenter: SearchPresenter<SearchMVPView>)
            : SearchMVPPresenter<SearchMVPView> = mainPresenter
}