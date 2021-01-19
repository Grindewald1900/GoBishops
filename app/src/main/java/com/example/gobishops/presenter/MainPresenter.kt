package com.example.gobishops.presenter

import com.example.gobishops.contract.MainContract
import com.example.gobishops.model.MainModel


/**
 * Created by Yee on 2021/1/18.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class MainPresenter(mView: MainContract.View): MainContract.Presenter{
    private val view: MainContract.View = mView
    private val model: MainContract.Model = MainModel()

    init {
        view.initMainView(true)
        model.setIsInitial(false)
    }


}