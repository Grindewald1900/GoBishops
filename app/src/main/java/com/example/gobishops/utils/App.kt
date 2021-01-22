package com.example.gobishops.utils

import android.app.Application
import android.content.Context


/**
 * Created by Yee on 2021/1/20.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class App: Application() {
    companion object{
        var mContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }

//    fun getContext(): Context{
//        return mContext!!
//    }
}