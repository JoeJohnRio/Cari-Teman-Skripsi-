package com.example.cariteman.ui.dashboard.presenter

import com.example.cariteman.data.model.Kelompok
import com.example.cariteman.data.model.MahasiswaResponse
import com.example.cariteman.data.model.Rekomendasi
import com.example.cariteman.data.model.RelationTeman
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.profile.view.ProfileMVPView

interface ProfileMVPPresenter <V : ProfileMVPView> : MVPPresenter<V> {

    fun getProfilInfoOthers(id: Int)

    fun getPengalamanAndRekomendasi(id: Int)

    fun getProfilInfoOthersItself()

    fun getPengalamanAndRekomendasiItself()

    fun showKelompok(idMahasiswa: Int)

    fun addFriend(relation: RelationTeman)

    fun confirmFriend(relation: RelationTeman)

    fun saveRekomendasi(rekomendasi: Rekomendasi)

    fun changeProfilePicture(mahasiswa: MahasiswaResponse)

    fun addHistoryLihatProfil(idMahasiswa: Int)

    fun addFriendToKelompok(kelompok: Kelompok)
}