package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface TambahKelompok1MVPView : MVPView{
    fun handleShowFriend(response: MutableList<DaftarTemanHanyaNama>)

    fun saveListOfTeman(response: DaftarTemanHanyaNama)

    fun deleteListOfTeman(response: DaftarTemanHanyaNama)

    fun goToKelompokDetail()
}