package com.example.gobishops.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.gobishops.R


/**
 * Created by Yee on 2021/4/6.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object ImageUtil {

    /**
     * Get random portrait from drawable
     */
    fun getRandomPortrait(context: Context): Int?{
        val rand = (1..10).random()
        var image: Int? = R.drawable.img_portrait1
        when(rand){
            1 -> {image = R.drawable.img_portrait1}
            2 -> {image = R.drawable.img_portrait2}
            3 -> {image = R.drawable.img_portrait3}
            4 -> {image = R.drawable.img_portrait4}
            5 -> {image = R.drawable.img_portrait5}
            6 -> {image = R.drawable.img_portrait6}
            7 -> {image = R.drawable.img_portrait7}
            8 -> {image = R.drawable.img_portrait8}
            9 -> {image = R.drawable.img_portrait9}
            10 -> {image = R.drawable.img_portrait10}


        }
        return image
    }
}