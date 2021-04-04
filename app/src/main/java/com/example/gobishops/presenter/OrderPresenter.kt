package com.example.gobishops.presenter

import com.example.gobishops.contract.OrderContract
import com.example.gobishops.model.OrderModel


/**
 * Created by Yee on 2021/4/4.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class OrderPresenter(view: OrderContract.View): OrderContract.Presenter {
    val mView: OrderContract.View = view
    val model = OrderModel()
    init {
        mView.initView()
    }
}