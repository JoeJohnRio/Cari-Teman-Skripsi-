package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunDetailFragment
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class VerifikasiAkunDetailFragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideVerifikasiAkunDetailFragmentProvider(): VerifikasiAkunDetailFragment
}