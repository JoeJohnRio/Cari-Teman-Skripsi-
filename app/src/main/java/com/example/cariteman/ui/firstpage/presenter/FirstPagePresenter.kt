package com.example.cariteman.ui.firstpage.presenter

import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.firstpage.view.FirstPageMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FirstPagePresenter<V : FirstPageMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    FirstPageMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

}

