package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class KelompokHomePresenter<V : KelompokHomeMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    KelompokHomeMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun showKelompok() {
        getView()?.let {
            addDisposable(mNetworkApi.showKelompok(getKey()).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                {response->
                    it.handleShowKelompok(response)
                },
                {
                    error->
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }

}
