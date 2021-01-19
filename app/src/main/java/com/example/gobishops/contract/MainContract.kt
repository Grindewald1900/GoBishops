package com.example.gobishops.contract

/**
 * Created by Yee on 2021/1/18.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
interface MainContract {
    /**
     * Interface of view
     */
    interface View: BaseContract.BaseView{
        fun initMainView(isInitial: Boolean)
        fun updateViewData()
    }

    interface Presenter: BaseContract.BasePresenter{

    }

    interface Model: BaseContract.BaseModel{
        fun getIsInitial(): Boolean
        fun setIsInitial(isInitial: Boolean)
    }


}