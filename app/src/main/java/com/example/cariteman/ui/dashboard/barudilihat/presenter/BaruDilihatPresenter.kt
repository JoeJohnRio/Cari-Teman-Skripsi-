package com.example.cariteman.ui.dashboard.barudilihat.presenter

import android.util.Log
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class BaruDilihatPresenter<V : BaruDilihatMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    BaruDilihatMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    lateinit var カウンタルルウプ: String

    override fun getHistoryProfilPkl(isRefresh: Boolean, pageNumber: Int) {
        getView()?.let {
            if (pageNumber == 0) getView()?.showProgress()
            addDisposable(mNetworkApi.getHistoryProfilPkl(
                getKey(),
                pageNumber + 1
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        getView().let {
                            it?.hideProgress()
                            if (!result.data.isNullOrEmpty()) {
                                it?.populateBaruDilihatProfil(result.data!!)
                                it?.setLastPageLimiter(result.lastPage!!)
                            }
                        }
                    },
                    { error ->
                        Log.d("error", error.message + "test")
                        getView()?.hideProgress()
                    }
                )
            )
        }
    }

    override fun getHistoryProfilLomba(isRefresh: Boolean, pageNumber: Int) {
        getView()?.let {
            if (pageNumber == 0) getView()?.showProgress()
            addDisposable(mNetworkApi.getHistoryProfilLomba(
                getKey(),
                pageNumber + 1
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        getView().let {
                            it?.hideProgress()
                            if (!result.data.isNullOrEmpty()) {
                                it?.populateBaruDilihatProfil(result.data!!)
                                it?.setLastPageLimiter(result.lastPage!!)
                            }
                        }
                    },
                    { error ->
                        Log.d("error", error.message + "test")
                        getView()?.hideProgress()
                    }
                )
            )
        }
    }

    override fun getHistoryProfilTempatPkl(isRefresh: Boolean, pageNumber: Int) {
        getView()?.let {
            if (pageNumber == 0) getView()?.showProgress()
            addDisposable(mNetworkApi.getHistoryProfilTempatPkl(
                getKey(),
                pageNumber + 1
            ).subscribeOn(
                IoScheduler()
            ).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        getView().let {
                            it?.hideProgress()
                            if (!result.data.isNullOrEmpty()) {
                                it?.populateBaruDilihatProfil(result.data!!)
                                it?.setLastPageLimiter(result.lastPage!!)
                            }
                        }
                    },
                    { error ->
                        Log.d("error", error.message + "test")
                        getView()?.hideProgress()
                    }
                )
            )
        }
    }

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


}