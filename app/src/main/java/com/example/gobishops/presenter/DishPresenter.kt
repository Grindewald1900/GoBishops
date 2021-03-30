package com.example.gobishops.presenter

import com.example.gobishops.contract.DishContract
import com.example.gobishops.contract.EventDetailContract
import com.example.gobishops.model.DishModel
import com.example.gobishops.model.EventDetailModel


/**
 * Created by Yee on 2021/3/29.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class DishPresenter(mView: DishContract.View): DishContract.Presenter {
    private var view: DishContract.View = mView
    private var model: DishContract.Model = DishModel()
    init {
        view.initView()
    }
}