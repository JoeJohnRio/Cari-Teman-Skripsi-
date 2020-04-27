package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class PengalamanHomePresenter<V : PengalamanHomeMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    PengalamanHomeMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getPengalamanLombaDanOrganisasi() {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.getPengalamanBoth(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { result ->
                    it.hideProgress()
                    it.setPengalamanAdapter(Mapper.pengalamanLombaMapper(result))
                }, { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message!!)
                }
            )
            )
        }
    }
}