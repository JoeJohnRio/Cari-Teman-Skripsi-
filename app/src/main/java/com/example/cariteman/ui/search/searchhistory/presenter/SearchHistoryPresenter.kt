package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeMVPView
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.SearchHistoryMVPView
import com.example.cariteman.util.Mapper
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

}