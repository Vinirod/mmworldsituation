package br.com.module.situationworld.utils

import android.graphics.Camera
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Build
import android.view.View
import android.view.animation.Animation
import java.lang.ref.WeakReference
import java.util.*

class AnimatorProxyUtils private constructor(view: View) : Animation() {
    private val mView: WeakReference<View>
    private val mCamera = Camera()
    private val mHasPivot = false
    private val mPivotX = 0f
    private val mPivotY = 0f
    private val mRotationX = 0f
    private val mRotationY = 0f
    private val mRotationZ = 0f
    private val mScaleX = 1f
    private val mScaleY = 1f
    private val mTranslationX = 0f
    private var mTranslationY = 0f
    private val mBefore = RectF()
    private val mAfter = RectF()
    private val mTempMatrix = Matrix()
    fun setTranslationY(translationY: Float) {
        if (mTranslationY != translationY) {
            prepareForUpdate()
            mTranslationY = translationY
            invalidateAfterUpdate()
        }
    }

    private fun prepareForUpdate() {
        val view = mView.get()
        view?.let { computeRect(mBefore, it) }
    }

    private fun invalidateAfterUpdate() {
        val view = mView.get()
        if (view == null || view.parent == null) {
            return
        }
        val after = mAfter
        computeRect(after, view)
        after.union(mBefore)
        (view.parent as View).invalidate(
            Math.floor(after.left.toDouble()).toInt(),
            Math.floor(after.top.toDouble()).toInt(),
            Math.ceil(after.right.toDouble()).toInt(),
            Math.ceil(after.bottom.toDouble()).toInt()
        )
    }

    private fun computeRect(r: RectF, view: View) {
        val w = view.width.toFloat()
        val h = view.height.toFloat()
        r[0f, 0f, w] = h
        val m = mTempMatrix
        m.reset()
        transformMatrix(m, view)
        mTempMatrix.mapRect(r)
        r.offset(view.left.toFloat(), view.top.toFloat())

        // Straighten coords if rotations flipped them
        if (r.right < r.left) {
            val f = r.right
            r.right = r.left
            r.left = f
        }
        if (r.bottom < r.top) {
            val f = r.top
            r.top = r.bottom
            r.bottom = f
        }
    }

    private fun transformMatrix(m: Matrix, view: View) {
        val w = view.width.toFloat()
        val h = view.height.toFloat()
        val hasPivot = mHasPivot
        val pX = if (hasPivot) mPivotX else w / 2f
        val pY = if (hasPivot) mPivotY else h / 2f
        val rX = mRotationX
        val rY = mRotationY
        val rZ = mRotationZ
        if (rX != 0f || rY != 0f || rZ != 0f) {
            val camera = mCamera
            camera.save()
            camera.rotateX(rX)
            camera.rotateY(rY)
            camera.rotateZ(-rZ)
            camera.getMatrix(m)
            camera.restore()
            m.preTranslate(-pX, -pY)
            m.postTranslate(pX, pY)
        }
        val sX = mScaleX
        val sY = mScaleY
        if (sX != 1.0f || sY != 1.0f) {
            m.postScale(sX, sY)
            val sPX = -(pX / w) * (sX * w - w)
            val sPY = -(pY / h) * (sY * h - h)
            m.postTranslate(sPX, sPY)
        }
        m.postTranslate(mTranslationX, mTranslationY)
    }

    companion object {
        val NEEDS_PROXY =
            Integer.valueOf(Build.VERSION.SDK) < Build.VERSION_CODES.HONEYCOMB
        private val PROXIES =
            WeakHashMap<View, AnimatorProxyUtils>()

        fun wrap(view: View): AnimatorProxyUtils {
            var proxy = PROXIES[view]
            if (proxy == null || proxy != view.animation) {
                proxy = AnimatorProxyUtils(view)
                PROXIES[view] = proxy
            }
            return proxy
        }
    }

    init {
        duration = 0
        fillAfter = true
        view.animation = this
        mView = WeakReference(view)
    }
}