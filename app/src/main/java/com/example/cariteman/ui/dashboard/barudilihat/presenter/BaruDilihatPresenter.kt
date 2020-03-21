package com.example.cariteman.ui.dashboard.barudilihat.presenter

import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BaruDilihatPresenter<V : BaruDilihatMVPView> @Inject internal constructor(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    BaruDilihatMVPPresenter<V> {

}