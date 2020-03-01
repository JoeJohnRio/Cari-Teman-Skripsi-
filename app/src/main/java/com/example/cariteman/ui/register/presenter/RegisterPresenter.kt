package com.example.cariteman.ui.register.presenter

import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.register.interactor.RegisterMVPInteractor
import com.example.cariteman.ui.register.view.RegisterMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RegisterPresenter<V : RegisterMVPView, I : RegisterMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), RegisterMVPPresenter<V, I> {
    override fun refreshQuestionCards(): Boolean? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDrawerOptionAboutClick(): Unit? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDrawerOptionRateUsClick(): Unit? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDrawerOptionFeedClick(): Unit? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNextRegisterClick() = getView()?.openAboutFragment()

}

