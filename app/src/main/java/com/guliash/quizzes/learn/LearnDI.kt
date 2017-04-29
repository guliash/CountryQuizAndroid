package com.guliash.quizzes.learn

import com.guliash.quizzes.learn.details.DetailsComponentProvider
import com.guliash.quizzes.learn.preview.PreviewComponentProvider
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
interface LearnComponent : PreviewComponentProvider, DetailsComponentProvider {

    fun inject(activity: LearnActivity)

}

@Module
abstract class LearnModule {

    @LearnScope
    @Binds
    abstract fun materialsProvider(materialsProvider: MaterialsProviderImpl): MaterialsProvider

}