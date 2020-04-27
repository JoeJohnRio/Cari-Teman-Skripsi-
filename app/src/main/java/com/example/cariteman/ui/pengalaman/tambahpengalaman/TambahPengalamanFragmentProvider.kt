package com.example.cariteman.ui.pengalaman.tambahpengalaman

import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanLombaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanOrganisasiFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TambahPengalamanFragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideTambahPengalamanOrganisasiFragment(): TambahPengalamanOrganisasiFragment

    @ContributesAndroidInjector
    abstract internal fun provideTambahPengalamanLombaFragment(): TambahPengalamanLombaFragment
}