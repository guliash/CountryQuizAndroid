package com.guliash.quizzes.learn.details

import com.guliash.quizzes.learn.details.view.DetailsFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named
import javax.inject.Scope

const val MATERIAL_ID = "details_material_id"

@DetailsScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {
    fun inject(fragment: DetailsFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DetailsScope

@Module
class DetailsModule(private val materialId: String) {

    @Provides
    @Named(MATERIAL_ID)
    @DetailsScope
    fun whichMaterial() = materialId

}

interface DetailsComponentProvider {
    fun createComponent(module: DetailsModule): DetailsComponent
}