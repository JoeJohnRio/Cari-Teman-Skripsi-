package com.example.cariteman.ui.dashboard.presenter

import com.example.cariteman.data.model.MahasiswaSearchFilter
import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.data.model.TempatPklSearchFilter
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.view.SearchMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class SearchPresenter<V : SearchMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    SearchMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

}
