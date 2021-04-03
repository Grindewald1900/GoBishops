package com.example.gobishops.presenter

import com.example.gobishops.contract.RegisterContract
import com.example.gobishops.model.RegisterModel


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class RegisterPresenter(mView: RegisterContract.View): RegisterContract.Presenter {
    private var view: RegisterContract.View = mView
    private var model: RegisterContract.Model = RegisterModel()
    init {
        view.initView()
    }
}