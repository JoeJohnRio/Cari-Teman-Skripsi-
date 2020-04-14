package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.dashboard.presenter.ProfileMVPPresenter
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.ui.profile.view.ProfileMVPView
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {
    @Provides
    internal fun provideProfilePresenter(mainPresenter: ProfilePresenter<ProfileMVPView>)
            : ProfileMVPPresenter<ProfileMVPView> = mainPresenter
}