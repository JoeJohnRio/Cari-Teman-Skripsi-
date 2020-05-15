package com.example.cariteman.ui.pengalaman.pengalamanhome

import com.example.cariteman.ui.search.filtersearch.view.FilterSearchFragment
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchHistoryFragmentProvider{

    @ContributesAndroidInjector
    abstract internal fun provideSearchHistoryFragmentProvider(): SearchHistoryFragment
}