package br.com.module.situationworld.utils

import android.os.Build
import android.transition.Explode
import android.view.Window

object VersionUtils {
    fun checkVersioAndEnableTransition(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            window.exitTransition = Explode()
        }
    }
}