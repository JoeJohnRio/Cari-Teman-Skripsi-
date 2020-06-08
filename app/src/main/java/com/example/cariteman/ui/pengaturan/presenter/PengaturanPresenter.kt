package com.example.cariteman.ui.pengaturan.presenter

import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.register.view.PengaturanMVPView
//import com.example.cariteman.ui.register.interactor.RegisterMVPInteractor
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PengaturanPresenter<V : PengaturanMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    PengaturanMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

}

