package com.example.sludd

import android.app.Application
import com.example.sludd.data.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SluddApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SluddApplication)
            modules(appModule)
        }
    }
}