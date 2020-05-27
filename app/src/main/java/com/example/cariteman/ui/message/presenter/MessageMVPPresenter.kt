package com.example.cariteman.ui.message.presenter

import com.example.cariteman.data.model.MessageSend
import com.example.cariteman.ui.base.presenter.MVPPresenter
import com.example.cariteman.ui.message.view.MessageMVPView

interface MessageMVPPresenter <V : MessageMVPView> : MVPPresenter<V> {
    fun showKelompok(idKelompok: Int)

    fun sendMessageKelompok(message: MessageSend)

    fun showUser(idMahasiswaPengirim: Int)

    fun sendMessageUser(message: MessageSend)

}