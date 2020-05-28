package com.example.cariteman.di.builder

import com.example.cariteman.ui.dashboard.*
import com.example.cariteman.ui.dashboard.view.DashboardBottomViewActivity
import com.example.cariteman.ui.dashboard.barudilihat.BaruDilihatModule
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatActivity
import com.example.cariteman.ui.login.LoginActivityModule
import com.example.cariteman.ui.login.LoginAdminActivityModule
import com.example.cariteman.ui.login.view.LoginActivity
import com.example.cariteman.ui.loginadmin.view.LoginAdminActivity
import com.example.cariteman.ui.message.view.MessageActivity
import com.example.cariteman.ui.notifikasi.view.NotifikasiActivity
import com.example.cariteman.ui.pengalaman.pengalamanhome.*
import com.example.cariteman.ui.pengalaman.tambahpengalaman.TambahPengalamanFragmentProvider
import com.example.cariteman.ui.pengalaman.view.KelompokActivity
import com.example.cariteman.ui.pengalaman.view.PengalamanActivity
import com.example.cariteman.ui.pengalaman.view.SearchActivity
import com.example.cariteman.ui.verifikasiakun.view.VerifikasiAkunActivity
import com.example.cariteman.ui.profile.tempatpklprofile.view.ProfileTempatPklActivity
import com.example.cariteman.ui.profile.view.ProfileActivity
import com.example.cariteman.ui.register.RegisterFragmentProvider
import com.example.cariteman.ui.register.RegisterActivityModule
import com.example.cariteman.ui.register.view.RegisterActivity
import com.example.cariteman.ui.search.frontprofile.FrontProfileFragmentProvider
import com.example.cariteman.ui.verifikasiakun.VerifikasiAkunModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by jyotidubey on 05/01/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(RegisterActivityModule::class), (RegisterFragmentProvider::class)])
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(LoginAdminActivityModule::class)])
    abstract fun bindLoginAdminActivity(): LoginAdminActivity

    @ContributesAndroidInjector(modules = [(DashboardModule::class), (DashboardFragmentProvider::class)])
    abstract fun bindDashboardActivity(): DashboardBottomViewActivity

    @ContributesAndroidInjector(modules = [(BaruDilihatModule::class)])
    abstract fun bindBaruDilihatActivity(): BaruDilihatActivity

    @ContributesAndroidInjector(modules = [(ProfileModule::class)])
    abstract fun bindProfilActivity(): ProfileActivity

    @ContributesAndroidInjector(modules = [(ProfileTempatPklModule::class)])
    abstract fun bindProfileTempatPklActivity(): ProfileTempatPklActivity

    @ContributesAndroidInjector(modules = [(MessageModule::class)])
    abstract fun bindMessageActivity(): MessageActivity

    @ContributesAndroidInjector(modules = [(VerifikasiAkunModule::class), (VerifikasiAkunListFragmentProvider::class), (VerifikasiAkunDetailFragmentProvider::class)])
    abstract fun bindVerifikasiAkunActivity(): VerifikasiAkunActivity

    @ContributesAndroidInjector(modules = [(NotifikasiModule::class), (DetailKelompokFragmentProvider::class)])
    abstract fun bindNotifikasiActivity(): NotifikasiActivity

    @ContributesAndroidInjector(modules = [(PengalamanModule::class), (TambahPengalamanFragmentProvider::class), (PengalamanHomeFragmentProvider::class), (BidangKerjaFragmentProvider::class)])
    abstract fun bindPengalamanActivity(): PengalamanActivity

    @ContributesAndroidInjector(modules = [(KelompokModule::class), (KelompokHomeFragmentProvider::class), (TambahKelompok1FragmentProvider::class), (TambahKelompok2FragmentProvider::class), DetailKelompokFragmentProvider::class])
    abstract fun bindKelompokActivity(): KelompokActivity

    @ContributesAndroidInjector(modules = [(SearchModule::class), (FilterSearchFragmentProvider::class), (FrontProfileFragmentProvider::class), (SearchHistoryFragmentProvider::class), (SkillHobiFragmentProvider::class)])
    abstract fun bindSearchActivity(): SearchActivity
}