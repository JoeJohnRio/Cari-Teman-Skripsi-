package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.pengalaman.pengalamanhome.view.TambahKelompok1Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TambahKelompok1FragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideTambahKelompok1Provider(): TambahKelompok1Fragment
}