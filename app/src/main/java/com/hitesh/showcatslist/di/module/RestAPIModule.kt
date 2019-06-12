package com.hitesh.showcatslist.di.module

import com.hitesh.showcatslist.api.PersonApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RestAPIModule {

    @Provides
    @Singleton
    internal fun providePersonApi(retrofit: Retrofit): PersonApi {
        return retrofit.create(PersonApi::class.java)
    }
}