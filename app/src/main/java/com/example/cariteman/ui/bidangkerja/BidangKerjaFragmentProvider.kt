package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.data.model.BidangKerja
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BidangKerjaFragmentProvider {

    @ContributesAndroidInjector
    abstract internal fun provideBidangKerjaProvider(): BidangKerjaFragment
}