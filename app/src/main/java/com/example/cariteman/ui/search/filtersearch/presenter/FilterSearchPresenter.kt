package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.FakultasResponse
import com.example.cariteman.data.model.KeminatanResponse
import com.example.cariteman.data.model.ProgramStudiResponse
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class FilterSearchPresenter<V : FilterSearchMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    FilterSearchMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi
    var fakultasResponse: List<FakultasResponse> = ArrayList()
    var programStudiResponse: List<ProgramStudiResponse> = listOf(ProgramStudiResponse(id = 0))
    var keminatanResponse: List<KeminatanResponse> = listOf(KeminatanResponse(id = 0))

    override fun getFakultasResponse() {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.getFakultas().subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                            it.hideProgress()
                            it.showFakultas(Mapper.fakultasResponseMapper(result))
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message!!)
                        it.backstack()
                    }
                ))
        }
    }

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

    override fun getProgramStudiResponse(id: Int) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.getProgramStudi(id = id).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        programStudiResponse = result
                        it.hideProgress()
                            it.showProgramStudi(Mapper.programStudiResponseMapper(result))
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message ?: "")
                        it.backstack()
                    }
                ))
        }
    }

    override fun getKeminatanResponse(position: Int) {
        getView()?.let {
            it.showProgress()
            val id: Int?
            if (position == 0) {
                id = 0
            } else {
                id = programStudiResponse[position - 1].id
            }
            addDisposable(mNetworkApi.getKeminatan(id = id).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        it.showKeminatan(Mapper.keminatanResponseMapper(result))
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message ?: "")
                        it.backstack()
                    }
                ))
        }
    }
}