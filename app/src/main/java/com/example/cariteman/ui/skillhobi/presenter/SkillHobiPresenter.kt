package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.SkillHobiMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class SkillHobiPresenter<V : SkillHobiMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    SkillHobiMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getSkillHobiSearch(namaSkillHobi: String) {
        getView()?.let {
            addDisposable(mNetworkApi.getSkillhobiSearch(
                getKey(),
                namaSkillHobi
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.showBidangKerja(result)
                },
                { error ->
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }

    override fun makeNewSkillHobi(namaSkillHobi: String) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.makeSkillHobi(
                getKey(),
                namaSkillHobi
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.hideProgress()
                    it.popBackStackWithBidangKerja(result)
                },
                { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }


}