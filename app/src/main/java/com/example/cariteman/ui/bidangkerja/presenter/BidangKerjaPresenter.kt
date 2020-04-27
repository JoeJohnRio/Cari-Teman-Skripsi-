package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class BidangKerjaPresenter<V : BidangKerjaMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    BidangKerjaMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getBidangKerjaSearch(namaBidangKerja: String) {
        getView()?.let {
            addDisposable(mNetworkApi.getBidangKerjaSearch(
                getKey(),
                namaBidangKerja
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.showBidangKerja(result)
                },
                {
                    error->it.showMessageToast(error.message!!)
                }
            ))
        }
    }

    override fun makeNewBidangKerja(namaBidangKerja: String) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.makeBidangKerja(getKey(), namaBidangKerja).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                {result->
                    it.hideProgress()
                    it.popBackStackWithBidangKerja(result)
                },
                {error ->
                    it.hideProgress()
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }


}