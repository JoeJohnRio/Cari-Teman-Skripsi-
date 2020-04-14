package com.example.cariteman.di.builder

import com.example.cariteman.ui.dashboard.DashboardModule
import com.example.cariteman.ui.dashboard.view.DashboardBottomViewActivity
import com.example.cariteman.ui.dashboard.DashboardFragmentProvider
import com.example.cariteman.ui.dashboard.ProfileModule
import com.example.cariteman.ui.dashboard.barudilihat.BaruDilihatModule
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatActivity
import com.example.cariteman.ui.login.LoginActivityModule
import com.example.cariteman.ui.login.view.LoginActivity
import com.example.cariteman.ui.profile.view.ProfileActivity
import com.example.cariteman.ui.register.RegisterFragmentProvider
import com.example.cariteman.ui.register.RegisterActivityModule
import com.example.cariteman.ui.register.view.Register1Activity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by jyotidubey on 05/01/18.
 */
@Module
abstract class ActivityBuilder {

//    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
//    abstract fun bindSplashActivity(): SplashMVPActivity

    @ContributesAndroidInjector(modules = [(RegisterActivityModule::class), (RegisterFragmentProvider::class)])
    abstract fun bindRegisterActivity(): Register1Activity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(DashboardModule::class), (DashboardFragmentProvider::class)])
    abstract fun bindDashboardActivity(): DashboardBottomViewActivity

    @ContributesAndroidInjector(modules = [(BaruDilihatModule::class)])
    abstract fun bindBaruDilihatActivity(): BaruDilihatActivity

    @ContributesAndroidInjector(modules = [(ProfileModule::class)])
    abstract fun bindProfilActivity(): ProfileActivity
//
//    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
//    abstract fun bindLoginActivity(): LoginActivity
//
//    @ContributesAndroidInjector(modules = [(BlogFragmentProvider::class), (OpenSourceFragmentProvider::class)])
//    abstract fun bindFeedActivity(): FeedActivity

}