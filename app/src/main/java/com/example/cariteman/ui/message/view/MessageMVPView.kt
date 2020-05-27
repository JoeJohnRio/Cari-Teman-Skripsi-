package com.example.cariteman.ui.message.view

import com.example.cariteman.data.model.MessageKelompok
import com.example.cariteman.ui.base.MVPView

interface MessageMVPView : MVPView {

    fun setMessage(responses: MutableList<MessageKelompok>)

}