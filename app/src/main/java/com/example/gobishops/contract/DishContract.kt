package com.example.gobishops.contract


/**
 * Created by Yee on 2021/3/29.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
interface DishContract {
    interface View: BaseContract.BaseView{}
    interface Presenter: BaseContract.BasePresenter{}
    interface Model: BaseContract.BaseModel{}
}