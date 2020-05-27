package com.example.cariteman.ui.notifikasi.view

import com.example.cariteman.data.model.MessageKelompok
import com.example.cariteman.data.model.NotifikasiResponse
import com.example.cariteman.ui.base.MVPView

interface NotifikasiMVPView : MVPView {
    fun showNotifikasi(responses: MutableList<NotifikasiResponse>)
}