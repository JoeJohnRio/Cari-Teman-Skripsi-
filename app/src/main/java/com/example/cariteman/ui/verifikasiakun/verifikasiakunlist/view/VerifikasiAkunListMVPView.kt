package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface VerifikasiAkunListMVPView : MVPView{
    fun handleMahasiswaNotVerified(responses: MutableList<MahasiswaNotVerifiedListResponse>)

    fun goToMahasiswaDetail(id: Int)
}