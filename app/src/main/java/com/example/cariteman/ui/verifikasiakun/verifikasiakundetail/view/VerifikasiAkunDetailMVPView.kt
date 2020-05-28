package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface VerifikasiAkunDetailMVPView : MVPView{
    fun showMahasiswaDetail(response: MahasiswaNotVerifiedListResponse)

    fun mahasiswaConfirmed()
}