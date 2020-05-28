package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.presenter

import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class VerifikasiAkunListPresenter<V : VerifikasiAkunListMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    VerifikasiAkunListMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun showMahasiswa() {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.showMahasiswaNotVerified().subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { response ->
                    it.hideProgress()
                    it.handleMahasiswaNotVerified(responses = response)
                },
                { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message ?: "")
                }
            ))
        }
    }

}
