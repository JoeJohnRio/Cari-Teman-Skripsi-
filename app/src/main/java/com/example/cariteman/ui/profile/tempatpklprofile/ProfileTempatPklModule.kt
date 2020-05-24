package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.profile.tempatpklprofile.presenter.ProfileTempatPklMVPPresenter
import com.example.cariteman.ui.profile.tempatpklprofile.presenter.ProfileTempatPklPresenter
import com.example.cariteman.ui.profile.tempatpklprofile.view.ProfileTempatPklMVPView
import dagger.Module
import dagger.Provides

@Module
class ProfileTempatPklModule {
    @Provides
    internal fun provideProfileTempatPklPresenter(mainPresenter: ProfileTempatPklPresenter<ProfileTempatPklMVPView>)
            : ProfileTempatPklMVPPresenter<ProfileTempatPklMVPView> = mainPresenter
}