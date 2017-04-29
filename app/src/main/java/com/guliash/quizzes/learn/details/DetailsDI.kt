package com.guliash.quizzes.learn.details

import com.guliash.quizzes.learn.details.view.DetailsFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named
import javax.inject.Scope

const val WHICH_MATERIAL = "details_which_material"

@DetailsScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {
    fun inject(fragment: DetailsFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DetailsScope

@Module
class DetailsModule(private val whichMaterial: Int) {

    @Provides
    @Named(WHICH_MATERIAL)
    fun whichMaterial() = whichMaterial

}

interface DetailsComponentProvider {
    fun createComponent(module: DetailsModule): DetailsComponent
}