package com.n16dccn159.admin.widget

import android.graphics.Matrix
import android.graphics.RectF
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView

class CropImageAnimation(private val imageView: ImageView, private val cropOverlayView: CropOverlayView) :
    Animation(), Animation.AnimationListener {

    private val startBoundPoints = FloatArray(8)

    private val endBoundPoints = FloatArray(8)

    private val startCropWindowRect = RectF()

    private val endCropWindowRect = RectF()

    private val startImageMatrix = FloatArray(9)

    private val endImageMatrix = FloatArray(9)

    private val animRect = RectF()

    private val animPoints = FloatArray(8)

    private val animMatrix = FloatArray(9)

    init {
        duration = 300
        fillAfter = true
        interpolator = AccelerateDecelerateInterpolator()
        setAnimationListener(this)
    }

    fun setStartState(boundPoints: FloatArray, imageMatrix: Matrix) {
        reset()
        System.arraycopy(boundPoints, 0, startBoundPoints, 0, 8)
        startCropWindowRect.set(cropOverlayView.getCropWindowRect())
        imageMatrix.getValues(startImageMatrix)
    }

    fun setEndState(boundPoints: FloatArray, imageMatrix: Matrix) {
        System.arraycopy(boundPoints, 0, endBoundPoints, 0, 8)
        endCropWindowRect.set(cropOverlayView.getCropWindowRect())
        imageMatrix.getValues(endImageMatrix)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {

        animRect.left =
            startCropWindowRect.left + (endCropWindowRect.left - startCropWindowRect.left) * interpolatedTime
        animRect.top =
            startCropWindowRect.top + (endCropWindowRect.top - startCropWindowRect.top) * interpolatedTime
        animRect.right =
            startCropWindowRect.right + (endCropWindowRect.right - startCropWindowRect.right) * interpolatedTime
        animRect.bottom =
            startCropWindowRect.bottom + (endCropWindowRect.bottom - startCropWindowRect.bottom) * interpolatedTime
        cropOverlayView.setCropWindowRect(animRect)

        for (i in animPoints.indices) {
            animPoints[i] = startBoundPoints[i] + (endBoundPoints[i] - startBoundPoints[i]) * interpolatedTime
        }
        cropOverlayView.setBounds(animPoints, imageView.width, imageView.height)

        for (i in animMatrix.indices) {
            animMatrix[i] = startImageMatrix[i] + (endImageMatrix[i] - startImageMatrix[i]) * interpolatedTime
        }
        val m = imageView.imageMatrix
        m.setValues(animMatrix)
        imageView.imageMatrix = m

        imageView.invalidate()
        cropOverlayView.invalidate()
    }

    override fun onAnimationStart(animation: Animation) {}

    override fun onAnimationEnd(animation: Animation) {
        imageView.clearAnimation()
    }

    override fun onAnimationRepeat(animation: Animation) {}
}
