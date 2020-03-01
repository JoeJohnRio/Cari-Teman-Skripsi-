package com.example.cariteman.ui.register.presenter

import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.register.interactor.RegisterMVPInteractor
import com.example.cariteman.ui.register.view.RegisterMVPView

interface RegisterMVPPresenter<V : RegisterMVPView, I : RegisterMVPInteractor> : MVPPresenter<V, I> {

    fun refreshQuestionCards(): Boolean?
    fun onDrawerOptionAboutClick() : Unit?
    fun onDrawerOptionRateUsClick(): Unit?
    fun onDrawerOptionFeedClick(): Unit?
    fun onNextRegisterClick() : Unit?

}