package com.example.cariteman.ui.search.frontprofile

import com.example.cariteman.ui.search.filtersearch.view.FrontProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FrontProfileFragmentProvider{

    @ContributesAndroidInjector
    abstract internal fun provideFrontProfileFragmentProvider(): FrontProfileFragment
}