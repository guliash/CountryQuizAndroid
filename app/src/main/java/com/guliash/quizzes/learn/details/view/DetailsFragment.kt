package com.guliash.quizzes.learn.details.view

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.guliash.quizzes.R
import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.core.ui.fragment.BaseFragment
import com.guliash.quizzes.learn.details.DetailsComponentProvider
import com.guliash.quizzes.learn.details.DetailsModule
import com.guliash.quizzes.learn.details.presenter.DetailsPresenter
import javax.inject.Inject

const val MATERIAL_ID = "material_id"

fun createDetailsFragment(materialId: String): DetailsFragment {
    val fragment = DetailsFragment()

    val bundle = Bundle()
    bundle.putString(MATERIAL_ID, materialId)
    fragment.arguments = bundle

    return fragment
}

class DetailsFragment : BaseFragment(), DetailsView {

    @BindView(R.id.image)
    lateinit var imageView: ImageView

    @BindView(R.id.collapsingToolbar)
    lateinit var collapsingToolbar: CollapsingToolbarLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.name)
    lateinit var nameTextView: TextView

    @BindView(R.id.description)
    lateinit var descriptionTextView: TextView

    @Inject
    lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as? DetailsComponentProvider)?.
                createComponent(DetailsModule(arguments.getString(MATERIAL_ID)))
                ?.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.learn_details_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        ButterKnife.bind(this, view!!)

        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }

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

        nameTextView.text = place.name
        descriptionTextView.text = place.description
    }
}