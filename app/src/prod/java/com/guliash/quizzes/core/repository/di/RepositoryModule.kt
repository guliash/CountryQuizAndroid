package com.guliash.quizzes.core.repository.di

import com.guliash.quizzes.core.api.Api
import com.guliash.quizzes.core.repository.Repository
import com.guliash.quizzes.core.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun api(): Api {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://guliash.com/quizzes/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun repository(api: Api): Repository = RepositoryImpl(api)
}