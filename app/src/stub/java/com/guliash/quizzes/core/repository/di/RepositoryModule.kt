package com.guliash.quizzes.core.repository.di

import com.guliash.quizzes.core.repository.Repository
import com.guliash.quizzes.core.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun repository(repository: RepositoryImpl): Repository
}