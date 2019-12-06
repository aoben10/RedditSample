package com.theobencode.victoroben.sampleproject

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.theobencode.victoroben.sampleproject.di.networkModule
import com.theobencode.victoroben.sampleproject.di.repositoryModule
import com.theobencode.victoroben.sampleproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)   
            modules(listOf(networkModule, viewModelModule, repositoryModule))
        }
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean = BuildConfig.DEBUG
        })
    }
}