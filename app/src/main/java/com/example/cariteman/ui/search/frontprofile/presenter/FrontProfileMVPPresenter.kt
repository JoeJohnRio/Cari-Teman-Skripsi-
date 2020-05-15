package com.example.cariteman.ui.pengalaman.pengalamanhome.presenter

import com.example.cariteman.data.model.MahasiswaSearchFilter
import com.example.cariteman.data.model.TempatPklSearchFilter
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.search.filtersearch.view.FrontProfileMVPView

interface FrontProfileMVPPresenter<V: FrontProfileMVPView>: MVPPresenter<V>{

    fun searchMahasiswa(mahasiswa: MahasiswaSearchFilter)

    fun searchTempatPkl(tempatPkl: TempatPklSearchFilter)

    fun toggleFavoriteFriend(idTarget: Int, isActive: Boolean)
}