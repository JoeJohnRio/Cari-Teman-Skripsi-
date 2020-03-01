package com.example.cariteman.ui.register

import com.example.cariteman.ui.register.view.Register2Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Register2Provider{
    @ContributesAndroidInjector
    abstract internal fun provideRegister2Fragment(): Register2Fragment
}