package com.guliash.quizzes.core.rx

import android.view.View
import io.reactivex.Observable

object RxView {
    fun clicks(view: View): Observable<Unit> {
        return Observable.create { emitter ->
            val clickListener = View.OnClickListener { emitter.onNext(Unit) }

            view.setOnClickListener(clickListener)

            emitter.setCancellable { view.setOnClickListener(null) }
        }
    }
}