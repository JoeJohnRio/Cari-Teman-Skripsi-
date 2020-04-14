package com.example.cariteman.ui.register.presenter

import android.os.Bundle
import android.widget.Toast
import com.example.cariteman.data.model.*
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.register.view.Register2Fragment
//import com.example.cariteman.ui.register.interactor.RegisterMVPInteractor
import com.example.cariteman.ui.register.view.RegisterMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RegisterPresenter<V : RegisterMVPView> @Inject internal constructor(schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable), RegisterMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    var fakultasResponse: List<FakultasResponse> = ArrayList()
    var programStudiResponse: List<ProgramStudiResponse> = listOf(ProgramStudiResponse(id = 0))
    var keminatanResponse: List<KeminatanResponse> = listOf(KeminatanResponse(id = 0))

    override fun onNextRegisterClick(){
        val bundle = Bundle()
        bundle.putString("message", "From Activity")
        var fragment2: Register2Fragment = Register2Fragment.newInstance()
        fragment2.arguments = bundle
        getView()?.openRegisterFragment()
    }

    override fun sendMahasiswaData(mahasiswa: Mahasiswa){
//        var mahasiswaTwoPkl = Mapper.mahasiswaToMahasiswaResponseMapper(mahasiswa)
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.registerApi(mahasiswa = mahasiswa).subscribeOn(IoScheduler()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        getView()?.showMessageToast("Sedang direview")
                        getView()?.hideProgress()
                    },
                    { error ->
                        getView()?.showMessageToast(error.message!!)
                        getView()?.hideProgress()
                    }
                ))

        }
    }

    override fun getFakultasResponse() {
        getView()?.let {
            addDisposable(mNetworkApi.getFakultas().subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        getView().let {
                            getView()?.showFakultas(Mapper.fakultasResponseMapper(result))
                        }
                    },
                    { error ->
                    }
                ) ) }
    }

    override fun getProgramStudiResponse(id: Int) {
        getView()?.let {
            addDisposable(mNetworkApi.getProgramStudi(id = id).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        programStudiResponse = result
                        getView().let {
                            getView()?.showProgramStudi(Mapper.programStudiResponseMapper(result))
                        }
                    },
                    { error ->
                    }
                ) ) }
    }

    override fun getKeminatanResponse(position: Int) {
        getView()?.let {
            val id: Int?
            if (position == 0){
                id = 0
            }else{
                id = programStudiResponse[position-1].id
            }
            addDisposable(mNetworkApi.getKeminatan(id = id).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        getView().let {
                            getView()?.showKeminatan(Mapper.keminatanResponseMapper(result))
                        }
                    },
                    { error ->
                    }
                ) ) }
    }

}

