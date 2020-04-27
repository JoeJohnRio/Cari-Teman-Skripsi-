package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PengalamanHomeFragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun providePengalamanHomeProvider(): PengalamanHomeFragment
}