package com.guliash.quizzes.learn.di

import com.guliash.quizzes.learn.view.MaterialFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MaterialScope

@MaterialScope
@Subcomponent(modules = arrayOf(MaterialModule::class))
interface MaterialComponent {

    fun inject(fragment: MaterialFragment)

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WhichMaterial

@Module
class MaterialModule(private val whichMaterial: Int) {

    @Provides
    @WhichMaterial
    fun whichMaterial() = whichMaterial

}

interface MaterialComponentProvider {
    fun createComponent(module: MaterialModule): MaterialComponent
}
