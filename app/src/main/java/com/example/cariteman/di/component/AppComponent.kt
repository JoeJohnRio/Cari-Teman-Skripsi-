package com.example.cariteman.di.component

import android.app.Application
import com.example.cariteman.MvpApp
import com.example.cariteman.di.module.AppModule
import com.example.cariteman.di.builder.ActivityBuilder
import com.example.cariteman.di.module.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (NetModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpApp)

}