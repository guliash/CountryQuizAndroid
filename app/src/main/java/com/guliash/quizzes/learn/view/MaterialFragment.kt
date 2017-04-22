package com.guliash.quizzes.learn.view

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
import com.guliash.quizzes.learn.di.MaterialComponentProvider
import com.guliash.quizzes.learn.di.MaterialModule
import com.guliash.quizzes.learn.presenter.MaterialPresenter
import javax.inject.Inject

val WHICH_MATERIAL_ARG = "which_material_arg"

fun createMaterialFragment(which: Int): MaterialFragment {
    val fragment = MaterialFragment()
    val bundle = Bundle()
    bundle.putInt(WHICH_MATERIAL_ARG, which)
    fragment.arguments = bundle
    return fragment
}

class MaterialFragment : BaseFragment(), MaterialView {

    @BindView(R.id.image)
    lateinit var imageView: ImageView

    @Inject
    lateinit var presenter: MaterialPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as? MaterialComponentProvider)?.
                createComponent(MaterialModule(arguments.getInt(WHICH_MATERIAL_ARG)))
                ?.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        println(container)
        return inflater.inflate(R.layout.learn_material_fragment, container, false)
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
        val which = arguments.getInt(WHICH_MATERIAL_ARG)
        if(which == 0) {
            imageView.background = null
        } else {
            imageView.setBackgroundColor(0x00e676)
        }
//        Glide.with(this)
//                .load(place.image.url)
//                .centerCrop()
//                .into(imageView)
    }
}