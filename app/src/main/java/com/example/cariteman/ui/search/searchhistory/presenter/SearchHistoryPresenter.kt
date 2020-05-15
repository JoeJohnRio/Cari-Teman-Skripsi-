package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchMVPView
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class SearchHistoryPresenter<V : SearchHistoryMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    SearchHistoryMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun addSearchHistory(searchHistory: SearchHistory) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.addSearchHistory(
                getKey(),
                searchHistory
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.hideProgress()
                },
                { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }

    override fun showSearchHistory() {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.showSearchHistory(
                getKey()
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.hideProgress()
                    it.showSearchHistory(result)
                },
                { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }
}