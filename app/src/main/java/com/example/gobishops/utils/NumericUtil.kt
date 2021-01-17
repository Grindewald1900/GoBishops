package com.example.gobishops.utils

import android.content.Context


/**
 * Created by Yee on 2021/1/17.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object NumericUtil {
    /**
     * Convert pixel to dp
     */
    fun pixelToDp(px: Int, context: Context): Int{
        val scale = context.resources.displayMetrics.density
        return (px * scale + 0.5f).toInt()
    }
}