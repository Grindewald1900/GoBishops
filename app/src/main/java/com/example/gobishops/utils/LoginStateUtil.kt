package com.example.gobishops.utils

import com.example.gobishops.entity.User


/**
 * Created by Yee on 2021/4/1.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object LoginStateUtil {
    private var user: User? = null
    private var isLogin: Boolean = false

    /**
     * Ordinary getter for user
     */
    fun getUser(): User?{
        return user
    }

    /**
     * Ordinary setter for user
     */
    fun setUser(user: User){
        this.user = user
    }

    /**
     * Ordinary getter for login state
     */
    fun getIsLogin(): Boolean{
        return isLogin
    }

    /**
     * Ordinary setter for login state
     */
    fun setIsLogin(isLogin: Boolean){
        this.isLogin = isLogin
    }

    /**
     * Return user id if logged in, otherwise return 0
     */
    fun getUserId(): Int{
        return if(isLogin){
            user!!.id
        }else{
            0
        }
    }
}