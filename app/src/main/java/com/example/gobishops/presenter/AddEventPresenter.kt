package com.example.gobishops.presenter

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gobishops.R
import com.example.gobishops.contract.AddEventContract
import com.example.gobishops.entity.Event
import com.example.gobishops.model.AddEventModel
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.DBUtil
import com.example.gobishops.view.AddEventActivity
import java.time.LocalDate


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

    override fun releaseEvent() {
        val event: Event = view.getInputData()
        DBUtil.addEntity(ConstantUtil.DATABASE_EVENT,event)
    }

    override fun refreshTitle(title: String) {
        TODO("Not yet implemented")
    }

    override fun clickTitle(isClickable: Boolean) {
        view.setTitleEditable(isClickable)
        model.setTitleClickable(isClickable)
    }
    override fun getUploadCounter(): Int {
        return model.getUploadCounter()
    }

    override fun addImageToView(uri: Uri?) {
        view.addImageView(uri)
        model.addUploadCounter()
    }


    override fun getInvitedUser(): ArrayList<String> {
        return model.getInvitedUser()
    }

    override fun addInvitedUser(userId: String) {
        model.addInvitedUser(userId)
    }

    override fun getImageSet(): ArrayList<String> {
        return model.getImageSet()
    }

    override fun addImageSet(imageID: String) {
        model.addImageSet(imageID)
    }

    override fun getEventID(): String {
        return model.getEventID()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getEventDate(): LocalDate {
       return model.getEventDate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setEventDate(date: LocalDate) {
        model.setEventDate(date)
    }


}