package com.example.cariteman.ui.dashboard.presenter

import android.util.Log
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class DashboardPresenter<V : DashboardMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    DashboardMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getHistoryDashboardLombaResponse() {
        getView()?.showProgress()
        getView()?.let {
            addDisposable(mNetworkApi.getHistoryDashboardLomba(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        it.populateLombaDanPklDashboard(result)
                    },
                    { error ->
                        it.hideProgress()
                        Log.d("error", error.message + "test")
                    }
                )
            )
        }
    }

    override fun getHistoryDashboardPklResponse() {
        getView()?.showProgress()
        getView()?.let {
            addDisposable(mNetworkApi.getHistoryDashboardPkl(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.populateLombaDanPklDashboard(result)
                        it.hideProgress()
                    },
                    { error ->
                        it.hideProgress()
                        Log.d("error", error.message + "test")
                    }
                )
            )
        }
    }

    override fun getHistoryDashboardTempatPklResponse() {
        getView()?.showProgress()
        getView()?.let {
            addDisposable(mNetworkApi.getHistoryDashboardTempatPkl(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        it.populateLombaDanPklDashboard(result)
                    },
                    { error ->
                        it.hideProgress()
                        Log.d("error", error.message + "test")
                    }
                )
            )
        }
    }

    override fun getFavoriteFriendLombaResponse(isRefresh: Boolean, pageNumber: Int) {
        getView()?.showProgress()
        getView()?.let {
            addDisposable(mNetworkApi.getDashboardFavoriteFriendLomba(
                getKey(),
                pageNumber
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        it.populateFavoriteProfil(result.data!!)
                        it.setLastPageLimiter(result.lastPage!!)
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message!!)
                        Log.d("error", error.message + "test")
                    }
                )
            )
        }
    }

    override fun getFavoriteFriendPklResponse(isRefresh: Boolean, pageNumber: Int) {
        getView()?.showProgress()
        getView()?.let {
            addDisposable(mNetworkApi.getDashboardFavoriteFriendPkl(
                getKey(),
                pageNumber
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        it.populateFavoriteProfil(result.data!!)
                        it.setLastPageLimiter(result.lastPage!!)
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message!!)
                        Log.d("error", error.message + "test")
                    }
                )
            )
        }
    }

    override fun getFavoriteTempatPklResponse(isRefresh: Boolean, pageNumber: Int) {
        getView()?.showProgress()
        getView()?.let {
            addDisposable(mNetworkApi.getDashboardFavoriteTempatPkl(
                getKey(),
                pageNumber
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        it.populateFavoriteProfil(result.data!!)
                        it.setLastPageLimiter(result.lastPage!!)
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message!!)
                        Log.d("error", error.message + "test")
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
                            it?.showMessageToast("Ditambahkan dalam daftar favorit")
                        } else {
                            it?.showMessageToast("Dihapus dari daftar favorit")
                        }
                    },
                    { error ->
                        it?.showMessageToast(error.message!!)
                        Log.d("error", error.message + "test")
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
