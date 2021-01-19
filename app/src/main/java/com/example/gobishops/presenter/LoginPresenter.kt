package com.example.gobishops.presenter

import com.example.gobishops.contract.BaseContract
import com.example.gobishops.contract.LoginContract
import com.example.gobishops.model.LoginModel


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class LoginPresenter(mView: LoginContract.View): LoginContract.Presenter {
    private var view: LoginContract.View = mView
    private var model: LoginContract.Model = LoginModel()
    init {
        view.initView()
    }
}