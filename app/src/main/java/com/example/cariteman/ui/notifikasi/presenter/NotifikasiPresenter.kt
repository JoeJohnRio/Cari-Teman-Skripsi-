package com.example.cariteman.ui.notifikasi.presenter

import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.notifikasi.view.NotifikasiMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class NotifikasiPresenter<V : NotifikasiMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    NotifikasiMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun showNotifikasi() {
        getView()?.let {
            addDisposable(mNetworkApi.showNotifikasi(
                getKey()
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { result ->
                    it.showNotifikasi(result)
                }, { error ->
                    it.showMessageToast(error.message ?: "")
                }
            )
            )
        }

    }


}
