package com.example.cariteman.ui.profile.tempatpklprofile.presenter

import com.example.cariteman.data.model.UlasanTempatPklProfile
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.profile.tempatpklprofile.view.ProfileTempatPklMVPView

interface ProfileTempatPklMVPPresenter <V : ProfileTempatPklMVPView> : MVPPresenter<V> {
    fun getProfilTempatPkl(id: Int)

    fun getUlasanTempatPkl(id: Int)

    fun saveUlasanTempatPkl(ulasanTempatPklProfile: UlasanTempatPklProfile)
}