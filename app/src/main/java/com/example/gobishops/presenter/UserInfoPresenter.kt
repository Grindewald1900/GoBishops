package com.example.gobishops.presenter

import com.example.gobishops.contract.UserInfoContract
import com.example.gobishops.model.UserInfoModel


/**
 * Created by Yee on 2021/2/25.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class UserInfoPresenter(mView: UserInfoContract.View): UserInfoContract.Presenter {
    private val view = mView
    private val model = UserInfoModel()
    init {
        view.initView()
    }
}