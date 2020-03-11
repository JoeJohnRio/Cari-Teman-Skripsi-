package com.example.cariteman.ui.dashboard.presenter

import com.example.cariteman.ui.base.presenter.BasePresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView
import com.example.cariteman.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DashboardPresenter<V : DashboardMVPView> @Inject internal constructor(schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable), DashboardMVPPresenter<V> {

}
