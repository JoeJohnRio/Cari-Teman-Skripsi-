package com.example.cariteman.ui.dashboard.barudilihat.presenter

import com.example.cariteman.data.model.RekomendasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.dashboard.barudilihat.view.RekomendasiMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class RekomendasiPresenter<V : RekomendasiMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    RekomendasiMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getHistoryProfilPkl(rekomendasiResponse: RekomendasiResponse) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.getDashboardRekomendasi(
                getKey(),
                rekomendasiResponse
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        if (!result.isNullOrEmpty()) {
                            it.populateRekomendasiProfile(result)
                        }

                    },
                    { error ->
                        it.showMessageToast(error.message ?: "")
                        it.hideProgress()
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