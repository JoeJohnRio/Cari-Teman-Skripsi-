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
        getView()?.showProgress()
        addDisposable(
            mNetworkApi.getPengalamanAndRekomendasi(
                getKey(),
                id
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                getView()?.setPengalamanAndRekomendasi(
                    response.rekomendasi,
                    Mapper.pengalamanLombaMapper(response.pengalaman)
                )
                getView()?.hideProgress()
            },
                { error ->
                    Log.d("error", "" + error.message!!)
                    getView()?.showMessageToast(error.message!!)
                    getView()?.hideProgress()
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

    override fun getProfilInfoOthersItself() {
        addDisposable(
            mNetworkApi.getProfilInfoOthersItself(
                getKey()
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                getView()?.setInfoProfil(response)
            },
                { error ->
                    getView()?.showMessageToast(error.message!!)
                })
        )
    }

    override fun getPengalamanAndRekomendasiItself() {
        getView()?.showProgress()
        addDisposable(
            mNetworkApi.getPengalamanAndRekomendasiItself(
                getKey()
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                getView()?.setPengalamanAndRekomendasi(
                    response.rekomendasi,
                    Mapper.pengalamanLombaMapper(response.pengalaman)
                )
                getView()?.hideProgress()
            },
                { error ->
                    Log.d("error", "" + error.message!!)
                    getView()?.showMessageToast(error.message!!)
                    getView()?.hideProgress()
                })
        )
    }

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
