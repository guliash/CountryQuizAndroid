package com.guliash.quizzes.core.di

import android.content.Context
import com.guliash.quizzes.core.utils.io.FileUtils
import com.guliash.quizzes.game.Repository
import com.guliash.quizzes.game.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun context(): Context = context

    @Provides
    @Singleton
    fun repository(fileUtils: FileUtils, context: Context): Repository = RepositoryImpl(fileUtils, context)
}
