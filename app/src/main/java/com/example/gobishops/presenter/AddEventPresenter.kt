package com.example.gobishops.presenter

import android.net.Uri
import com.example.gobishops.R
import com.example.gobishops.contract.AddEventContract
import com.example.gobishops.model.AddEventModel
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.view.AddEventActivity


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class AddEventPresenter(mView: AddEventContract.View): AddEventContract.Presenter {
    private var model: AddEventModel = AddEventModel()
    private var view: AddEventContract.View = mView
    init {
        view.initView()
    }

    /**
     * Choose image from gallery, up to 5 slices
     */
    override fun chooseImage(str: String) {
        if (model.getUploadCounter() <= ConstantUtil.MAX_UPLOAD_IMAGE){
            view.selectImage()
        }else{
            view.showToast(str)
        }
    }

    override fun addImageToView(uri: Uri?) {
        view.addImageView(uri)
        model.addUploadCounter()
    }
}