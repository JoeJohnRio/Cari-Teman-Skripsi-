package com.example.cariteman.ui.dashboard.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.profile.view.ProfileMVPView

interface ProfileMVPPresenter <V : ProfileMVPView> : MVPPresenter<V> {

    fun getProfilInfoOthers(id: Int)

}