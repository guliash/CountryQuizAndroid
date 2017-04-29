package com.guliash.quizzes.learn.preview

import com.guliash.quizzes.learn.preview.view.PreviewFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MaterialScope

@MaterialScope
@Subcomponent(modules = arrayOf(PreviewModule::class))
interface PreviewComponent {

    fun inject(fragment: PreviewFragment)

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WhichMaterial

@Module
class PreviewModule(private val whichMaterial: Int) {

    @Provides
    @WhichMaterial
    fun whichMaterial() = whichMaterial

}

interface PreviewComponentProvider {
    fun createComponent(module: PreviewModule): PreviewComponent
}
