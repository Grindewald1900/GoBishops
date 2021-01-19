package com.example.gobishops.model

import android.os.Bundle
import com.example.gobishops.contract.MainContract


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class MainModel: MainContract.Model{
    private var isInitial: Boolean = true

    override fun getIsInitial() = isInitial
    override fun setIsInitial(isInitial: Boolean) {
        this.isInitial = isInitial
    }

}