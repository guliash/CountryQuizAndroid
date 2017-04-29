package com.guliash.quizzes.learn.preview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.guliash.quizzes.R
import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.core.ui.fragment.BaseFragment
import com.guliash.quizzes.learn.preview.PreviewComponentProvider
import com.guliash.quizzes.learn.preview.PreviewModule
import com.guliash.quizzes.learn.preview.presenter.PreviewPresenter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import javax.inject.Inject

val WHICH_MATERIAL_ARG = "which_material_arg"

fun createMaterialFragment(which: Int): PreviewFragment {
    val fragment = PreviewFragment()
    val bundle = Bundle()
    bundle.putInt(WHICH_MATERIAL_ARG, which)
    fragment.arguments = bundle
    return fragment
}

class PreviewFragment : BaseFragment(), PreviewView {

    @BindView(R.id.root)
    lateinit var rootView: View

    @BindView(R.id.image)
    lateinit var imageView: ImageView

    @Inject
    lateinit var presenter: PreviewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as? PreviewComponentProvider)?.
                createComponent(PreviewModule(arguments.getInt(WHICH_MATERIAL_ARG)))
                ?.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.learn_preview_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view!!)

        presenter.bind(this)
    }

    override fun onDestroyView() {
        presenter.unbind()

        super.onDestroyView()
    }

    override fun showMaterial(place: Place) {
        Glide.with(this)
                .load(place.image.url)
                .centerCrop()
                .into(imageView)
    }

    override fun selections(): Observable<Any> = RxView.clicks(rootView)
}