package com.hitesh.showcatslist.di.component

import com.hitesh.showcatslist.catlist.MainActivity
import com.hitesh.showcatslist.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface MyAppComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): MyAppComponent
        fun networkModule(networkModule: NetworkModule): Builder
    }
}