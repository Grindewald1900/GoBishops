package com.example.gobishops.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import com.example.gobishops.R

class TestActivityUtil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_util)
    }

    fun screenScale(){
        val rootView = window.findViewById<View>(android.R.id.content)
        val scale: Float = 0.6f
        val scaleAnimation = ScaleAnimation(1f, scale, 1f, scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.interpolator = LinearInterpolator()
        scaleAnimation.duration = 300
        scaleAnimation.fillAfter = true
        rootView.startAnimation(scaleAnimation)
    }
}