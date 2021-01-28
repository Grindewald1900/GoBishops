package com.example.gobishops.transformers

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.gobishops.utils.ConstantUtil


/**
 * Created by Yee on 2021/1/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ZoomOutPageTransformer: ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when{
                // If the page is off-screen to the left
                position < -2 -> {
//                    alpha = 0f
                }
                position <= 2 -> {
                    val scaleFactor = java.lang.Math.max(com.example.gobishops.utils.ConstantUtil.MIN_TRANSFORM_SCALE, 1 - java.lang.Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if(position < 0){
                        horzMargin - vertMargin / 2
                    }else {
                        horzMargin + vertMargin / 2
                    }
                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
//                    alpha = (ConstantUtil.MIN_TRANSFORM_ALPHA +
//                            (((scaleFactor - ConstantUtil.MIN_TRANSFORM_SCALE) / (1 - ConstantUtil.MIN_TRANSFORM_SCALE)) * (1 - ConstantUtil.MIN_TRANSFORM_ALPHA)))
                }
                else -> {
                    // This page is way off-screen to the right.
//                    alpha = 0f
                }
            }
        }

    }
}