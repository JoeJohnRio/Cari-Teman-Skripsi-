package com.example.cariteman.di.module

import android.app.Application
import android.content.Context
import com.example.cariteman.data.network.ApiHelper
import com.example.cariteman.data.network.AppApiHelper
import com.example.cariteman.data.preferences.AppPreferenceHelper
import com.example.cariteman.data.preferences.PreferenceHelper
import com.example.cariteman.di.builder.PreferenceInfo
import com.example.cariteman.util.AppConstants
import com.example.cariteman.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper = appPreferenceHelper

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()


}