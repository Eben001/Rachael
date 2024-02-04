package com.gana.ebenezer.rachael

import android.app.Application
import com.gana.ebenezer.rachael.di.appModule
import com.gana.ebenezer.rachael.di.remote
import com.gana.ebenezer.rachael.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                remote,
                repositoryModule
            )
        }
    }
}