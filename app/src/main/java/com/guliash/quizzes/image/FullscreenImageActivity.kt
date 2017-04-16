package com.guliash.quizzes.image

import android.os.Bundle
import android.view.WindowManager
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
        setContentView(R.layout.image_activity)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ButterKnife.bind(this)
        Glide.with(this).load(intent.getStringExtra(IMAGE_URL_EXTRA)).into(imageView)
    }
}