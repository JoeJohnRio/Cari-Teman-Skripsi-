package com.example.cariteman.di.builder

import com.example.cariteman.ui.register.Register2Provider
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

    @ContributesAndroidInjector(modules = [(RegisterActivityModule::class), (Register2Provider::class)])
    abstract fun bindRegisterActivity(): Register1Activity
//
//    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
//    abstract fun bindLoginActivity(): LoginActivity
//
//    @ContributesAndroidInjector(modules = [(BlogFragmentProvider::class), (OpenSourceFragmentProvider::class)])
//    abstract fun bindFeedActivity(): FeedActivity

}