package com.example.cariteman.ui.dashboard.presenter

import android.util.Log
import androidx.annotation.MainThread
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.profile.view.ProfileMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class ProfilePresenter<V : ProfileMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    ProfileMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getPengalamanAndRekomendasi(id: Int) {
        addDisposable(
            mNetworkApi.getPengalamanAndRekomendasi(
                getKey(),
                id
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                getView()?.setPengalamanAndRekomendasi(Mapper.pengalamanLombaMapper(response.pengalaman))
            },
                { error ->
                    getView()?.showMessageToast(error.message!!)
                })
        )
    }

    override fun getProfilInfoOthers(id: Int) {
        addDisposable(
            mNetworkApi.getProfilInfoOthers(
                getKey(),
                id
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                getView()?.setInfoProfil(response)
            },
                { error ->
                    getView()?.showMessageToast(error.message!!)
                })
        )
    }


}
