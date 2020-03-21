package com.example.cariteman.ui.base.presenter

import com.example.cariteman.ui.base.MVPView

interface MVPPresenter<V : MVPView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

    fun getKey(): String?

    fun setKey(key: String?)

}