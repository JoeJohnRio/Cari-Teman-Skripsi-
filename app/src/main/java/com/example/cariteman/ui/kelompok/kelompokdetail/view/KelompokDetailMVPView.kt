package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface KelompokDetailMVPView : MVPView{
    fun showAnggotaKelompokWithoutRemove(response: MutableList<AnggotaKelompok>)

    fun updateAfterConfirmKelompok(status: Int)
}