package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.dashboard.view.FavoriteFragment
import com.example.cariteman.ui.dashboard.view.HomeFragment
import com.example.cariteman.ui.dashboard.view.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardFragmentProvider {
    @ContributesAndroidInjector
    abstract internal fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract internal fun provideProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract internal fun provideFavoriteFragment(): FavoriteFragment
}