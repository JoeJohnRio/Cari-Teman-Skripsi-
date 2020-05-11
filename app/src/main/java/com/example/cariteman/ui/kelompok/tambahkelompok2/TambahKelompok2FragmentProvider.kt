package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.pengalaman.pengalamanhome.view.TambahKelompok2Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TambahKelompok2FragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideTambahKelompok2Provider(): TambahKelompok2Fragment
}