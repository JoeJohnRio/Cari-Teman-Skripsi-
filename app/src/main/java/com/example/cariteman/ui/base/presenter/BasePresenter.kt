package com.example.cariteman.ui.base.presenter

import com.example.cariteman.ui.base.MVPView
//import com.example.cariteman.ui.base.interactor.MVPInteractor
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : MVPView> internal constructor(protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) : MVPPresenter<V> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}