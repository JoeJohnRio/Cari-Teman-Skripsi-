package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class KelompokHomeFragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideKelompokHomeProvider(): KelompokHomeFragment
}