package com.example.androidhomework2sem.di.component

import android.content.Context
import com.example.androidhomework2sem.di.module.AppModule
import com.example.androidhomework2sem.di.module.NetModule
import com.example.androidhomework2sem.di.module.ServiceModule
import com.example.androidhomework2sem.di.module.ViewModelModule
import com.example.androidhomework2sem.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, AppModule::class, NetModule::class, ServiceModule::class])
interface AppComponent {

    fun getContext(): Context

    fun inject(activity: MainActivity)
}
