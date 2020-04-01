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
            it.showProgress()
            addDisposable(mNetworkApi.getHistoryProfilPkl(getKey(), pageNumber+1).subscribeOn(IoScheduler()).observeOn(
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
                        getView().let {
                            it?.hideProgress()
                        }
                    }
                )
            )
        }
    }

}