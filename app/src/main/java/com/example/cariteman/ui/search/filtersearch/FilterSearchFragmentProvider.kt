package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.search.filtersearch.view.FilterSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FilterSearchFragmentProvider{

    @ContributesAndroidInjector
    abstract internal fun provideSearchHistoryFragmentProvider(): FilterSearchFragment
}