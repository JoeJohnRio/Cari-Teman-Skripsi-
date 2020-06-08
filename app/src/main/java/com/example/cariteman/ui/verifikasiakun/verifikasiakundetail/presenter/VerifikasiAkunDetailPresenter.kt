package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.presenter

import com.example.cariteman.data.model.MahasiswaNotVerifiedListResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunDetailMVPView
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class VerifikasiAkunDetailPresenter<V : VerifikasiAkunDetailMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    VerifikasiAkunDetailMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun confirmMahasiswa(response: MahasiswaNotVerifiedListResponse) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.showMahasiswaNotVerifiedConfirm(response).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { response ->
                    it.hideProgress()
                    it.showMessageToast(response.message ?: "sudah dikonfirmasi")
                    it.mahasiswaConfirmed()
                },
                { error ->
                    it.showMessageToast(error.message ?: "")
                    it.hideProgress()
                }
            ))
        }
    }

    override fun getMahasiswaDetail(response: MahasiswaNotVerifiedListResponse) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.showMahasiswaNotVerifiedDetail(response).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { response ->
                    it.hideProgress()
                    it.showMahasiswaDetail(response)
                },
                { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message ?: "")
                }
            ))
        }
    }

}
