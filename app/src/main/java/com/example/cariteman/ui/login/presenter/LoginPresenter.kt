package com.example.cariteman.ui.login.presenter

import com.example.cariteman.data.model.Login
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.login.view.LoginMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class LoginPresenter<V : LoginMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    LoginMVPPresenter<V> {

    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun onLoginBtnClicked(login: Login) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.loginApi(login).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            )
                .subscribe(
                    { result ->
                        it.hideProgress()
                        if (!result.error.isNullOrEmpty()) {
                            it.showMessageToast(result.error ?: "")
                        } else {
                            it.goToDashboard()
                            it.saveData(result.token.toString())
                        }
                    },
                    { error ->
                        getView().let {
                            it?.hideProgress()
                        }
                    }
                ))
        }
    }

}

