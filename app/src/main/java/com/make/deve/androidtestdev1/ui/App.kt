package com.make.deve.androidtestdev1.ui

import android.app.Application
import com.make.deve.androidtestdev1.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@App)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(
                Modules.getAllModules()
            )
        }

    }

    companion object {
        lateinit var instance: App private set
    }
}