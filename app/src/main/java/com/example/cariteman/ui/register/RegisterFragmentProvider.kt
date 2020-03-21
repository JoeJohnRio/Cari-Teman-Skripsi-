package com.example.cariteman.ui.register

import com.example.cariteman.ui.register.view.Register2Fragment
import com.example.cariteman.ui.register.view.Register3Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RegisterFragmentProvider {
    @ContributesAndroidInjector
    abstract internal fun provideRegister2Fragment(): Register2Fragment

    @ContributesAndroidInjector
    abstract internal fun provideRegister3Fragment(): Register3Fragment
}