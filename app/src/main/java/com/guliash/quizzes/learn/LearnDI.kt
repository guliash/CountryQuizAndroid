package com.guliash.quizzes.learn

import com.guliash.quizzes.core.repository.Repository
import com.guliash.quizzes.learn.details.DetailsComponentProvider
import com.guliash.quizzes.learn.preview.PreviewComponentProvider
import com.guliash.quizzes.learn.preview.presenter.PreviewPresenter
import com.guliash.quizzes.learn.service.MaterialsProvider
import com.guliash.quizzes.learn.service.MaterialsProviderImpl
import dagger.Module
import dagger.Provides
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
class LearnModule(private val previewCommander: PreviewPresenter.Commander) {

    @LearnScope
    @Provides
    fun previewCommander(): PreviewPresenter.Commander = previewCommander

    @LearnScope
    @Provides
    fun materialsProvider(repository: Repository): MaterialsProvider {
        return MaterialsProviderImpl(repository)
    }

}