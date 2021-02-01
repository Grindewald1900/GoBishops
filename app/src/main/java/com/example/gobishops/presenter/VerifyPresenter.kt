package com.example.gobishops.presenter

import com.example.gobishops.contract.VerifyContract
import com.example.gobishops.model.VerifyModel


/**
 * Created by Yee on 2021/1/31.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class VerifyPresenter(mView: VerifyContract.View): VerifyContract.Presenter {
    private val view: VerifyContract.View = mView
    private val model: VerifyContract.Model = VerifyModel()

    init {
        view.initView()
    }
}