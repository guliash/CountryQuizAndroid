package com.guliash.quizzes.image

import android.os.Bundle
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.guliash.quizzes.R
import com.guliash.quizzes.core.ui.activity.BaseActivity

val IMAGE_URL_EXTRA = "image_url"

class FullscreenImageActivity : BaseActivity() {

    @BindView(R.id.image)
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_image_activity)

        ButterKnife.bind(this)
        Glide.with(this).load(intent.getStringExtra(IMAGE_URL_EXTRA)).into(imageView)
    }
}