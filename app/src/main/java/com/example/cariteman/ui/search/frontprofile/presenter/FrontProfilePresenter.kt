package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.*
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchMVPView
import com.example.cariteman.ui.search.filtersearch.view.FrontProfileMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class FrontProfilePresenter<V : FrontProfileMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    FrontProfileMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun toggleFavoriteFriend(idTarget: Int, isActive: Boolean) {
        getView()?.let {
            addDisposable(mNetworkApi.toggleFavoriteFriend(
                getKey(),
                idTarget
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        if (isActive) {
                            getView()?.showMessageToast("Ditambahkan ke dalam daftar favorit")
                        } else {
                            getView()?.showMessageToast("Dihapus dari daftar favorit")
                        }

                    },
                    { error ->
                        error.message?.let { getView()?.showMessageToast(it) }
                    }
                )
            )
        }
    }

    override fun toggleFavoriteTempatPkl(idTarget: Int, isActive: Boolean) {
        getView()?.let {
            addDisposable(mNetworkApi.toggleFavoriteTempatPkl(
                getKey(),
                idTarget
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        if (isActive) {
                            getView()?.showMessageToast("Ditambahkan ke dalam daftar favorit")
                        } else {
                            getView()?.showMessageToast("Dihapus dari daftar favorit")
                        }
                    },
                    { error ->
                        error.message?.let { getView()?.showMessageToast(it) }
                    }
                )
            )
        }
    }

    override fun searchMahasiswa(mahasiswa: MahasiswaSearchFilter) {
        getView()?.let {
            addDisposable(mNetworkApi.searchMahasiswa(
                getKey(),
                mahasiswa
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.handleSearchMahasiswa(result)
                },
                { error ->
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }

    override fun searchTempatPkl(tempatPkl: TempatPklSearchFilter) {
        getView()?.let {
            addDisposable(mNetworkApi.searchTempatPkl(
                getKey(),
                tempatPkl
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.handleSearchTempatPkl(result)
                },
                { error ->
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }


}