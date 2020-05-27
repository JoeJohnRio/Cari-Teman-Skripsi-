package com.example.cariteman.ui.message.presenter

import com.example.cariteman.data.model.MessageSend
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.message.view.MessageMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class MessagePresenter<V : MessageMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    MessageMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun showKelompok(idKelompok: Int) {
        getView()?.let {
            addDisposable(mNetworkApi.showMessageKelompok(
                getKey(),
                MessageSend(idKelompok = idKelompok)
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { result ->
                    it.setMessage(result)
                }, { error ->
                    it.showMessageToast(error.message ?: "")
                }
            )
            )
        }
    }

    override fun sendMessageKelompok(message: MessageSend) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.sendMessageKelompok(
                getKey(),
                message
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { result ->
                    showKelompok(message.idKelompok!!)
                    it.hideProgress()
                }, { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message ?: "")
                }
            )
            )
        }

    }

    override fun showUser(idMahasiswaPengirim: Int) {
        getView()?.let {
            addDisposable(mNetworkApi.showMessageUser(
                getKey(),
                MessageSend(idMahasiswaPengirim = idMahasiswaPengirim)
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { result ->
                    it.setMessage(result)
                }, { error ->
                    it.showMessageToast(error.message ?: "")
                }
            )
            )
        }
    }

    override fun sendMessageUser(message: MessageSend) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.sendMessageUser(
                getKey(),
                message
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { result ->
                    showUser(message.idMahasiswaPenerima!!)
                    it.hideProgress()
                }, { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message ?: "")
                }
            )
            )
        }
    }


}
