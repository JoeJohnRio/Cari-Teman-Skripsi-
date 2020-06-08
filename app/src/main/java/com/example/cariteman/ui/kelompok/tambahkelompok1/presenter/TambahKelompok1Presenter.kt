package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.Kelompok
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.TambahKelompok1MVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class TambahKelompok1Presenter<V : TambahKelompok1MVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    TambahKelompok1MVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun showFriendNotAddedYet(idKelompok: Int) {getView()?.let {
        addDisposable(mNetworkApi.showFriendNotAdded(getKey(), idKelompok).subscribeOn(IoScheduler()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe(
            { result ->
                it.handleShowFriend(result)
            },
            { error ->
                it.showMessageToast(error.message!!)
            }
        ))
    }
    }

    override fun showFriendWithNameOnly() {
        getView()?.let {
            addDisposable(mNetworkApi.showFriendWithNamaOnly(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { result ->
                    it.handleShowFriend(result)
                },
                { error ->
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }

    override fun addFriendToKelompok(kelompok: Kelompok) {
        getView()?.let {
            addDisposable(mNetworkApi.addFriendToKelompok(
                getKey(),
                kelompok
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.showMessageToast("Teman berhasil ditambahkan")
                    it.goToKelompokDetail()
                },
                { error ->
                    it.showMessageToast(error.message ?: "")
                }
            ))
        }
    }

}
