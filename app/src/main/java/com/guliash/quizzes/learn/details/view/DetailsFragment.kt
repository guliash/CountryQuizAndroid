package com.guliash.quizzes.learn.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guliash.quizzes.R
import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.core.ui.fragment.BaseFragment

const val WHICH_MATERIAL = "which_material"

fun createDetailsFragment(whichMaterial: Int): DetailsFragment {
    val fragment = DetailsFragment()

    val bundle = Bundle()
    bundle.putInt(WHICH_MATERIAL, whichMaterial)
    fragment.arguments = bundle

    return fragment
}

class DetailsFragment : BaseFragment(), DetailsView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.learn_details_fragment, container, false)
    }

    override fun showMaterial(place: Place) {

    }
}