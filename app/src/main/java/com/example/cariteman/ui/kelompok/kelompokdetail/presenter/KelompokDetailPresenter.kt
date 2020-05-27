package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokDetailMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class KelompokDetailPresenter<V : KelompokDetailMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    KelompokDetailMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getAnggotaKelompok(idKelompok: Int) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.showAnggotaKelompok(
                getKey(),
                idKelompok
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.hideProgress()
                    it.showAnggotaKelompokWithoutRemove(result)
                },
                {

                        error ->
                    it.hideProgress()
                    it.showMessageToast(error.message!!)
                }
            ))
        }
    }

    override fun confirmAnggotaKelompok(relationKelompok: RelationKelompok) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.confirmAnggotaKelompok(
                getKey(),
                relationKelompok
            ).subscribeOn(
                IoScheduler()
            ).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.hideProgress()
                    it.updateAfterConfirmKelompok(relationKelompok.status ?: 0)
                },
                { error ->
                    it.hideProgress()
                    it.updateAfterConfirmKelompok(relationKelompok.status ?: 0)
                }
            ))
        }
    }


}
