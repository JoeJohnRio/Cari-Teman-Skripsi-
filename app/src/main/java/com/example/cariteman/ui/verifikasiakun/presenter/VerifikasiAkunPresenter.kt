package com.example.cariteman.ui.verifikasiakun.presenter

import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.verifikasiakun.view.VerifikasiAkunMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class VerifikasiAkunPresenter<V : VerifikasiAkunMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    VerifikasiAkunMVPPresenter<V> {


}
