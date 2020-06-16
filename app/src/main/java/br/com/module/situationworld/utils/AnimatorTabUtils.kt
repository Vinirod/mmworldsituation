package br.com.module.situationworld.utils

import android.view.View

object AnimatorTabUtils {
    fun setTranslationY(view: View, translationY: Float) {
        if (AnimatorProxyUtils.NEEDS_PROXY) {
            AnimatorProxyUtils.wrap(view).setTranslationY(translationY)
        } else {
            Honeycomb.setTranslationY(view, translationY)
        }
    }

    private object Honeycomb {
        fun setTranslationY(view: View, translationY: Float) {
            view.translationY = translationY
        }
    }
}