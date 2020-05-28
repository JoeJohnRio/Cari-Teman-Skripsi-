package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class VerifikasiAkunListFragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideVerifikasiAkunListFragmentProvider(): VerifikasiAkunListFragment
}