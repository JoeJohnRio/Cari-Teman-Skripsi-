package com.example.cariteman.ui.pengaturan

import com.example.cariteman.ui.pengaturan.view.PengaturanFragment
import com.example.cariteman.ui.register.view.Register1Fragment
import com.example.cariteman.ui.register.view.Register2Fragment
import com.example.cariteman.ui.register.view.Register3Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PengaturanFragmentProvider {
    @ContributesAndroidInjector
    abstract internal fun providePengaturanFragment(): PengaturanFragment
}