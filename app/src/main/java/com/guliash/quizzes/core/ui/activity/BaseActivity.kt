package com.guliash.quizzes.core.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import timber.log.Timber

open class BaseActivity : AppCompatActivity() {
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

    override fun onRestart() {
        Timber.d("${this}: onRestart")
        super.onRestart()
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

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Timber.d("${this}: onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Timber.d("${this}: onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

}
