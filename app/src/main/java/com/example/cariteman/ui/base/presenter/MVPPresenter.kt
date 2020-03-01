package com.example.cariteman.ui.base.presenter

import com.example.cariteman.ui.base.MVPView
import com.example.cariteman.ui.base.interactor.MVPInteractor

interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}