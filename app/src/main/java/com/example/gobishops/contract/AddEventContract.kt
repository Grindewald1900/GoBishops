package com.example.gobishops.contract

import android.net.Uri
import com.example.gobishops.entity.Event
import java.time.LocalDate

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
        fun getInputData(): Event
        fun refreshDate(year: Int, month: Int, dayOfMonth: Int)
        fun refreshTitle(title: String)
        fun checkEventInfo(event: Event)
        fun setTitleEditable(isEditable: Boolean)
    }

    interface Presenter: BaseContract.BasePresenter{
        fun chooseImage(str: String)
        fun getUploadCounter(): Int
        fun addImageToView(uri: Uri?)
        fun getEventDate(): LocalDate
        fun setEventDate(date: LocalDate)
        fun getInvitedUser(): ArrayList<String>
        fun addInvitedUser(userId: String)
        fun getImageSet(): ArrayList<String>
        fun addImageSet(imageID: String)
        fun getEventID(): String
        fun releaseEvent()
        fun clickTitle(isClickable: Boolean)
        fun refreshTitle(title: String)
    }

    interface Model: BaseContract.BaseModel{
        fun getUploadCounter(): Int
        fun addUploadCounter()
        fun getEventDate(): LocalDate
        fun setEventDate(date: LocalDate)
        fun getInvitedUser(): ArrayList<String>
        fun addInvitedUser(userId: String)
        fun getImageSet(): ArrayList<String>
        fun addImageSet(imageID: String)
        fun getEventID(): String
        fun getTitleClickable(): Boolean
        fun setTitleClickable(clickable: Boolean)
    }

}