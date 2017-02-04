package com.guliash.quizzes.core.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class Presenter<T> {

    protected var view: T? = null
    private val disposables: CompositeDisposable = CompositeDisposable()

    open fun bind(view: T) {
        this.view = view
    }

    open fun unbind() {
        unsubscribe()
        view = null
    }

    protected fun subscribe(vararg disposables: Disposable) {
        this.disposables.addAll(*disposables)
    }

    protected fun unsubscribe() = disposables.clear()

}