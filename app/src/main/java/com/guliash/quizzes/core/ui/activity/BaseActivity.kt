package com.guliash.quizzes.core.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
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

    override fun onRestart() {
        println("${this}: onRestart")
        super.onRestart()
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

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        println("${this}: onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        println("${this}: onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

}
