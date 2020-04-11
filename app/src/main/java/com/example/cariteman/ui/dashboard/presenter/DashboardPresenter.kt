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

class DashboardPresenter<V : DashboardMVPView> @Inject internal constructor(schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable), DashboardMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getHistoryDashboardLombaResponse() {
        getView()?.let {
            addDisposable(mNetworkApi.getHistoryDashboardLomba(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        getView().let {
                            if (!result.isNullOrEmpty()){
                                it?.populateLombaDanPklDashboard(result)
                            }
                        }
                    },
                    { error ->
                        Log.d("error", error.message+"test")
                    }
                )
            )
        }
    }

    override fun getHistoryDashboardPklResponse() {
        getView()?.let {
            addDisposable(mNetworkApi.getHistoryDashboardPkl(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        getView().let {
                            if (!result.isNullOrEmpty()){
                                it?.populateLombaDanPklDashboard(result)
                            }
                        }
                    },
                    { error ->
                        Log.d("error", error.message+"test")
                    }
                )
            )
        }
    }

    override fun getHistoryDashboardTempatPklResponse() {
        getView()?.let {
            addDisposable(mNetworkApi.getHistoryDashboardTempatPkl(getKey()).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        getView().let {
                            if (!result.isNullOrEmpty()){
                                it?.populateLombaDanPklDashboard(result)
                            }
                        }
                    },
                    { error ->
                        Log.d("error", error.message+"test")
                    }
                )
            )
        }
    }
}
