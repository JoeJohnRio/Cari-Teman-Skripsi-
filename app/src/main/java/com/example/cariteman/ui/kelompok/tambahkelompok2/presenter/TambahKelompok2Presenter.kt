package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.Kelompok
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.TambahKelompok2MVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class TambahKelompok2Presenter<V : TambahKelompok2MVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    TambahKelompok2MVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun makeKelompok(kelompok: Kelompok) {
        getView()?.let {
            addDisposable(mNetworkApi.makeKelompok(getKey(), kelompok).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    result->it.showMessageToast("data berhasil ditambahkan")
                    it.returnToHome()
                },
                {
                    error->it.showMessageToast(error.message!!)
                }
            ))
        }
    }
}