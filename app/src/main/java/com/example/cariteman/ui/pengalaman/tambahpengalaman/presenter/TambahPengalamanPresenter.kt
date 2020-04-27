package com.example.cariteman.ui.pengalaman.tambahpengalaman.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class TambahPengalamanPresenter<V : TambahPengalamanMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    TambahPengalamanMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun modifyPengalamanOrganisasi(response: PengalamanLombaOrganisasiResponse) {
        getView()?.let {
            addDisposable(
                mNetworkApi.modifyPengalamanOrganisasi(
                    getKey(),
                    response
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { result ->
//                        it.showMessageToast(result)
                        it.getBackToPengalamanHome()
                    }, { error ->
                        it.showMessageToast(error.message!!)
                    })
            )
        }
    }

    override fun modifyPengalamanLomba(response: PengalamanLombaOrganisasiResponse) {
        getView()?.let {
            addDisposable(
                mNetworkApi.modifyPengalamanLomba(
                    getKey(),
                    response
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { result ->
                        it.getBackToPengalamanHome()
                    }, { error ->
                        it.showMessageToast(error.message!!)
                    })
            )
        }
    }


}