package com.example.cariteman.ui.profile.tempatpklprofile.presenter

import com.example.cariteman.data.model.UlasanTempatPklProfile
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.profile.tempatpklprofile.view.ProfileTempatPklMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class ProfileTempatPklPresenter<V : ProfileTempatPklMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    ProfileTempatPklMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun getUlasanTempatPkl(id: Int) {
        getView()?.let {
            addDisposable(
                mNetworkApi.getUlasanTempatPkl(
                    getKey(),
                    id
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.setUlasan(response)
                    },
                    { error ->
                        getView()?.showMessageToast(error.message ?: "")
                    })
            )
        }
    }

    override fun saveUlasanTempatPkl(ulasanTempatPklProfile: UlasanTempatPklProfile) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.saveUlasanTempatPkl(
                    getKey(),
                    ulasanTempatPklProfile
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.hideProgress()
                        ulasanTempatPklProfile.idTempatPkl?.let {
                            getUlasanTempatPkl(it)
                        }
                        it.showMessageToast(response.message ?: "")
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message ?: "")
                    })
            )
        }
    }

    override fun getProfilTempatPkl(id: Int) {
        getView()?.let {
            addDisposable(
                mNetworkApi.getTempatPklProfile(
                    getKey(),
                    id
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.setInfoProfil(response)
                        getUlasanTempatPkl(id)
                    },
                    { error ->
                        getView()?.showMessageToast(error.message ?: "")
                    })
            )
        }
    }

}
