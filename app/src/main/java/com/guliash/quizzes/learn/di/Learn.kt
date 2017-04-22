package com.guliash.quizzes.learn.di

import com.guliash.quizzes.learn.LearnActivity
import com.guliash.quizzes.learn.service.MaterialsProvider
import com.guliash.quizzes.learn.service.MaterialsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LearnScope

@LearnScope
@Subcomponent(modules = arrayOf(LearnModule::class))
interface LearnComponent : MaterialComponentProvider {

    fun inject(activity: LearnActivity)

}

@Module
abstract class LearnModule {

    @LearnScope
    @Binds
    abstract fun materialsProvider(materialsProvider: MaterialsProviderImpl): MaterialsProvider

}