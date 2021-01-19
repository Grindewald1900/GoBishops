package com.example.gobishops.contract

import android.os.Bundle


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
interface BaseContract {
    interface BaseView{
        fun initView()
        fun showToast(str: String)
    }
    interface BaseModel{

    }
    interface BasePresenter{

    }
}