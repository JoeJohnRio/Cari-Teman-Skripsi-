package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface KelompokHomeMVPView : MVPView{
    fun handleShowKelompok(response: MutableList<RelationKelompok>)

    fun goToKelompokDetail(id: Int, jumlahAnggota: Int, namaKelompok: String)
}