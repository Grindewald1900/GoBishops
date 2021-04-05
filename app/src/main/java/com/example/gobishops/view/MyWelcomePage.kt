package com.example.gobishops.view

import android.R
import com.stephentuso.welcome.BasicPage
import com.stephentuso.welcome.TitlePage
import com.stephentuso.welcome.WelcomeActivity
import com.stephentuso.welcome.WelcomeConfiguration


/**
 * Created by Yee on 2021/4/4.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class MyWelcomePage: WelcomeActivity() {
    override fun configuration(): WelcomeConfiguration {
        return WelcomeConfiguration.Builder(this)
            .defaultBackgroundColor(com.example.gobishops.R.color.colorPrimary)
            .page(
                TitlePage(
                    com.example.gobishops.R.drawable.ic_welcome_1,
                    "Recommend Restaurant"
                )
            )
            .page(
                TitlePage(
                    com.example.gobishops.R.drawable.ic_welcome_2,
                    "Provide directions"
                ).background(com.example.gobishops.R.color.facebook)
            )
            .page(
                TitlePage(
                    com.example.gobishops.R.drawable.ic_welcome_3,
                    "Place your Order"
                ).background(com.example.gobishops.R.color.green_1)
            )
            .page(
                TitlePage(
                    com.example.gobishops.R.drawable.ic_welcome_4,
                    "Eat with friends"
                ).background(com.example.gobishops.R.color.pink_1)
            )
            .swipeToDismiss(true)
            .build()
    }
}