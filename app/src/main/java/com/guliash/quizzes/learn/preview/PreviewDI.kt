package com.guliash.quizzes.learn.preview

import com.guliash.quizzes.learn.preview.view.PreviewFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named
import javax.inject.Scope

const val WHICH_MATERIAL = "preview_which_material"

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PreviewScope

@PreviewScope
@Subcomponent(modules = arrayOf(PreviewModule::class))
interface PreviewComponent {

    fun inject(fragment: PreviewFragment)

}

@Module
class PreviewModule(private val whichMaterial: Int) {

    @Provides
    @Named(WHICH_MATERIAL)
    fun whichMaterial() = whichMaterial

}

interface PreviewComponentProvider {
    fun createComponent(module: PreviewModule): PreviewComponent
}
