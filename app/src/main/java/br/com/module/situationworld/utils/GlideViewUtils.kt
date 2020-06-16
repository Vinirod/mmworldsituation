package br.com.module.situationworld.utils

import android.content.Context
import android.widget.ImageView
import br.com.module.situationworld.R
import br.com.module.situationworld.views.activities.LaunchScreenActivity
import com.bumptech.glide.Glide

object GlideViewUtils {

    @JvmStatic
    fun initGlidView(context: Context, imgbg: Int, mImgBackground: ImageView) {
        Glide
            .with(context)
            .load(imgbg)
            .into(mImgBackground)
    }
}