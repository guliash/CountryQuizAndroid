package com.guliash.quizzes.core.ui.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import timber.log.Timber

open class BaseDialogFragment : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("${this}: onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        Timber.d("${this}: onDestroy")
        super.onDestroy()
    }

    override fun onStart() {
        Timber.d("${this}: onStart")
        super.onStart()
    }

    override fun onStop() {
        Timber.d("${this}: onStop")
        super.onStop()
    }

    override fun onResume() {
        Timber.d("${this}: onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.d("${this}: onPause")
        super.onPause()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        Timber.d("${this}: onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        Timber.d("${this}: onDestroyView")
        super.onDestroyView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Timber.d("${this}: onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Timber.d("${this}: onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }
}