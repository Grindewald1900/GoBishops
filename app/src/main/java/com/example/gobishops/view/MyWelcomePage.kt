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
                    com.example.gobishops.R.drawable.ic_baseline_sentiment_very_dissatisfied_24,
                    "Title"
                )
            )
            .page(
                BasicPage(
                    com.example.gobishops.R.drawable.ic_baseline_sentiment_neutral_24,
                    "Header",
                    "More text."
                ).background(com.example.gobishops.R.color.google)
            )
            .page(
                BasicPage(
                    com.example.gobishops.R.drawable.ic_baseline_sentiment_satisfied_alt_24,
                    "Lorem ipsum",
                    "dolor sit amet."
                ).background(com.example.gobishops.R.color.facebook)
            )
            .swipeToDismiss(true)
            .build()
    }
}