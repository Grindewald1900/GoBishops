package com.example.gobishops.presenter

import com.example.gobishops.contract.EventDetailContract
import com.example.gobishops.model.EventDetailModel


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class EventDetailPresenrer(mView: EventDetailContract.View): EventDetailContract.Presenter {
    private var view: EventDetailContract.View = mView
    private var model: EventDetailContract.Model = EventDetailModel()
    init {
        view.initView()
    }
}