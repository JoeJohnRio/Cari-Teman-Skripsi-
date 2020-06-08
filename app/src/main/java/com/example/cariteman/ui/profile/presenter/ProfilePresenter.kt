package com.example.cariteman.ui.dashboard.presenter

import android.util.Log
import androidx.annotation.MainThread
import com.example.cariteman.data.model.Kelompok
import com.example.cariteman.data.model.MahasiswaResponse
import com.example.cariteman.data.model.Rekomendasi
import com.example.cariteman.data.model.RelationTeman
import com.example.cariteman.data.network.INetworkApi
import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.profile.view.ProfileMVPView
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class ProfilePresenter<V : ProfileMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    ProfileMVPPresenter<V> {
    @Inject
    lateinit var mNetworkApi: INetworkApi

    override fun changeProfilePicture(mahasiswa: MahasiswaResponse) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.changeProfilPicture(
                    getKey(),
                    mahasiswa
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.showMessageToast(response.message ?: "")
                        it.hideProgress()
                    },
                    { error ->
                        it.showMessageToast(error.message ?: "")
                        it.hideProgress()
                    })
            )
        }
    }

    override fun addHistoryLihatProfil(idMahasiswa: Int) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.addHistoryLihatProfil(
                    getKey(),
                    idMahasiswa
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.hideProgress()
                    },
                    { error ->
                        it.showMessageToast(error.message ?: "")
                        it.hideProgress()
                    })
            )
        }
    }

    override fun addFriendToKelompok(kelompok: Kelompok) {
        getView()?.let {
            addDisposable(mNetworkApi.addFriendToKelompok(
                getKey(),
                kelompok
            ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    it.showMessageToast("Teman berhasil ditambahkan")
                    it.handleAfterInviteToKelompok()
                },
                { error ->
                    it.showMessageToast(error.message ?: "")
                }
            ))
        }
    }

    override fun saveRekomendasi(rekomendasi: Rekomendasi) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.saveRekomendasi(
                    getKey(),
                    rekomendasi
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.showMessageToast(response.message ?: "")
                        getPengalamanAndRekomendasi(rekomendasi.idPenerima ?: 0)
                        it.hideProgress()
                    },
                    { error ->
                        //                        it.showMessageToast(error.message ?: "")
                        getPengalamanAndRekomendasi(rekomendasi.idPenerima ?: 0)
                        it.hideProgress()
                    })
            )
        }
    }

    override fun addFriend(relation: RelationTeman) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.addFriend(
                    getKey(),
                    relation
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.showMessageToast(response.message ?: "Menunggu persetujuan")
                        it.hideProgress()
                        it.restartActivity()
                    },
                    { error ->
                        Log.d("error", "" + error.message!!)
                        it.showMessageToast(error.message!!)
                        it.hideProgress()
                    })
            )
        }
    }

    override fun confirmFriend(relation: RelationTeman) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.confirmFriend(
                    getKey(),
                    relation
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.showMessageToast(response.message ?: "")
                        it.hideProgress()
                        it.restartActivity()
                    },
                    { error ->
                        Log.d("error", "" + error.message!!)
                        it.showMessageToast(error.message!!)
                        it.hideProgress()
                    })
            )
        }
    }

    override fun getPengalamanAndRekomendasi(id: Int) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.getPengalamanAndRekomendasi(
                    getKey(),
                    id
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                    it.setPengalamanAndRekomendasi(
                        response.rekomendasi,
                        Mapper.pengalamanLombaMapper(response.pengalaman)
                    )
                    it.hideProgress()
                },
                    { error ->
                        it.showMessageToast(error.message ?: "")
                        it.hideProgress()
                    })
            )
        }
    }

    override fun getProfilInfoOthers(id: Int) {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.getProfilInfoOthers(
                    getKey(),
                    id
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.hideProgress()
                        it.setInfoProfil(response)
                    },
                    { error ->
                        it.hideProgress()
                        it.showMessageToast(error.message ?: "")
                    })
            )
        }
    }

    override fun getProfilInfoOthersItself() {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.getProfilInfoOthersItself(
                    getKey()
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.hideProgress()
                        getView()?.setInfoProfil(response)
                    },
                    { error ->
                        it.hideProgress()
                        getView()?.showMessageToast(error.message ?: "")
                    })
            )
        }
    }

    override fun getPengalamanAndRekomendasiItself() {
        getView()?.let {
            it.showProgress()
            addDisposable(
                mNetworkApi.getPengalamanAndRekomendasiItself(
                    getKey()
                ).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { response ->
                        it.setPengalamanAndRekomendasi(
                            response.rekomendasi,
                            Mapper.pengalamanLombaMapper(response.pengalaman)
                        )
                        it.hideProgress()
                    },
                    { error ->
                        it.showMessageToast(error.message ?: "")
                        it.hideProgress()
                    })
            )
        }

    }

    override fun showKelompok(idMahasiswa: Int) {
        getView()?.let {
            it.showProgress()
            addDisposable(mNetworkApi.showKelompokNotInvitedYet(
                getKey(),
                idMahasiswa
            ).subscribeOn(IoScheduler()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { response ->
                    it.hideProgress()
                    it.handleShowKelompok(response)
                },
                { error ->
                    it.hideProgress()
                    it.showMessageToast(error.message ?: "")
                }
            ))
        }
    }


}
