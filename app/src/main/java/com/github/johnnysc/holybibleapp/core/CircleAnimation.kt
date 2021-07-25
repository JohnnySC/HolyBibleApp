package com.github.johnnysc.holybibleapp.core

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author Asatryan on 19.07.2021
 */
class CircleAnimation(private val view: View, private val radius: Float) : Animation() {
    private var cx = 0f
    private var cy = 0f

    private var prevX = 0f
    private var prevY = 0f

    private var prevDx = 0f
    private var prevDy = 0f

    override fun willChangeBounds() = true

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        val cxImage = width / 2
        val cyImage = height / 2
        cx = (view.left + cxImage).toFloat()
        cy = (view.top + cyImage).toFloat()
        prevX = cx
        prevY = cy
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        if (interpolatedTime == 0f) {
            t.matrix.setTranslate(prevDx, prevDy)
            return
        }
        val angleDeg = (interpolatedTime * 360f + 90) % 360
        val angleRad = Math.toRadians(angleDeg.toDouble()).toFloat()

        val x = (cx + radius * cos(angleRad.toDouble())).toFloat()
        val y = (cy + radius * sin(angleRad.toDouble())).toFloat()
        val dx = prevX - x
        val dy = prevY - y
        prevX = x
        prevY = y
        prevDx = dx
        prevDy = dy
        t.matrix.setTranslate(dx, dy)
    }
}