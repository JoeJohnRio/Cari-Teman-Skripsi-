package com.example.cariteman.ui.dashboard.presenter

import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.pengalaman.view.KelompokMVPView
import com.example.cariteman.ui.pengalaman.view.PengalamanMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class KelompokPresenter<V : KelompokMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    KelompokMVPPresenter<V> {


}
