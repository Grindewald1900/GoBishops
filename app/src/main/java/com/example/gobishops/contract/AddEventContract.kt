package com.example.gobishops.contract

import android.net.Uri

/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
interface AddEventContract {
    /**
     * Interface of view
     */
    interface View: BaseContract.BaseView{
        fun selectImage()
        fun addImageView(imageUri: Uri?)
    }

    interface Presenter: BaseContract.BasePresenter{
        fun chooseImage(str: String)
        fun addImageToView(uri: Uri?)
    }

    interface Model: BaseContract.BaseModel{
        fun getUploadCounter(): Int
        fun addUploadCounter()
    }

}