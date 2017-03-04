package com.guliash.quizzes.map.di

import com.guliash.quizzes.map.view.MapActivity
import dagger.Subcomponent

@MapScope
@Subcomponent(modules = arrayOf(MapModule::class))
interface MapComponent {
    fun inject(mapActivity: MapActivity)
}