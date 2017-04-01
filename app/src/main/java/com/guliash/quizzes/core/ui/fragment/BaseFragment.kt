package com.guliash.quizzes.core.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

open class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("${this}: onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        println("${this}: onDestroy")
        super.onDestroy()
    }

    override fun onStart() {
        println("${this}: onStart")
        super.onStart()
    }

    override fun onStop() {
        println("${this}: onStop")
        super.onStop()
    }

    override fun onResume() {
        println("${this}: onResume")
        super.onResume()
    }

    override fun onPause() {
        println("${this}: onPause")
        super.onPause()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        println("${this}: onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        println("${this}: onDestroyView")
        super.onDestroyView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        println("${this}: onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        println("${this}: onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }
}