package com.example.gobishops.contract


/**
 * Created by Yee on 2021/4/4.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
interface OrderContract: BaseContract {
    interface View: BaseContract.BaseView{}
    interface Model: BaseContract.BaseModel{}
    interface Presenter: BaseContract.BasePresenter{}
}