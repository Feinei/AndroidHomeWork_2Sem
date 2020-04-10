package com.example.androidhomework2sem

import android.app.Application
import com.example.androidhomework2sem.di.component.AppComponent
import com.example.androidhomework2sem.di.component.DaggerAppComponent
import com.example.androidhomework2sem.di.module.AppModule
import com.example.androidhomework2sem.di.module.NetModule
import com.example.androidhomework2sem.di.module.ServiceModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule())
            .serviceModule(ServiceModule())
            .build()
    }

    companion object {

        lateinit var appComponent: AppComponent
    }
}