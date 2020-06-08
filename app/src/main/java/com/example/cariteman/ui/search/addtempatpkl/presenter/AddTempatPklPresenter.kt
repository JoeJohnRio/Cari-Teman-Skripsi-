package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.data.model.TempatPklResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.search.filtersearch.view.AddTempatPklMVPView
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchMVPView
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class AddTempatPklPresenter<V : AddTempatPklMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    AddTempatPklMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getLokasiPkl() {
        getView()?.let {
        it.showProgress()
        addDisposable(mNetworkApi.getLokasiPkl().subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { result ->
                it.handleLokasiPkl(result)
                it.hideProgress()
            },
            { error ->
                it.hideProgress()
                it.showMessageToast(error.message!!)
            }
        ))
    }
    }

    override fun addTempatPkl(tempatPkl: TempatPklResponse) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.addTempatPkl(
                getKey(),
                tempatPkl
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.handleAfterAddLokasiPkl()
                    it.hideProgress()
                },
                { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }

}