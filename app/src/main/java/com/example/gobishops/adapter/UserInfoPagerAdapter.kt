package com.example.gobishops.adapter

import `in`.galaxyofandroid.awesometablayout.AwesomeTabBarAdapter
import android.R
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


/**
 * Created by Yee on 2021/3/29.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class UserInfoPagerAdapter(fragmentManager: FragmentManager?) :
    AwesomeTabBarAdapter(fragmentManager) {
    var fragments: ArrayList<Fragment> = ArrayList()
    var titles: ArrayList<String> = ArrayList()
    var colors = intArrayOf(
        com.example.gobishops.R.color.white,
        com.example.gobishops.R.color.white,
    )
    var textColors = intArrayOf(com.example.gobishops.R.color.colorPrimary,com.example.gobishops.R.color.colorPrimary)
    var icons = intArrayOf(
        com.example.gobishops.R.drawable.ic_baseline_format_list_bulleted_24,
        com.example.gobishops.R.drawable.ic_md_favorite_border_color1,

        )

    init {
            fragments.add(Fragment())
            fragments.add(Fragment())

            titles.add("Orders")
            titles.add("Favorites")
        }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getColorResource(position: Int): Int {
        return colors[position]
    }

    override fun getTextColorResource(position: Int): Int {
        return textColors[position]
    }

    override fun getIconResource(position: Int): Int {
        return icons[position]
    }
}