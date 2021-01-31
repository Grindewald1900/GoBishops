package com.example.gobishops.presenter

import com.example.gobishops.contract.ForgetPwdContract
import com.example.gobishops.model.ForgetPwdModel


/**
 * Created by Yee on 2021/1/31.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ForgetPwdPresenter(mView: ForgetPwdContract.View): ForgetPwdContract.Presenter {
    private val view: ForgetPwdContract.View = mView
    private val model: ForgetPwdContract.Model = ForgetPwdModel()
    init {
        view.initView()
    }
}