package com.example.gobishops.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gobishops.contract.AddEventContract
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class AddEventModel: AddEventContract.Model {
    @RequiresApi(Build.VERSION_CODES.O)
    private var eventDate: LocalDate = LocalDate.now()
    private var uploadCounter: Int = 0
    private var invitedUser: ArrayList<String> = ArrayList()
    private var imageSet: ArrayList<String> = ArrayList()
    private val uuid = UUID.randomUUID().toString()
    private var isTitleClickable: Boolean = false

    override fun getUploadCounter() = uploadCounter

    override fun addUploadCounter() {
        uploadCounter++
    }

    override fun getInvitedUser(): ArrayList<String> = invitedUser

    override fun addInvitedUser(userId: String) {
        invitedUser.add(userId)
    }
    override fun getImageSet(): ArrayList<String> = imageSet

    override fun addImageSet(imageID: String) {
        imageSet.add(imageID)
    }

    override fun getEventID(): String = uuid

    override fun getTitleClickable(): Boolean = isTitleClickable

    override fun setTitleClickable(clickable: Boolean) {
        isTitleClickable = clickable
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getEventDate(): LocalDate = eventDate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setEventDate(date: LocalDate) {
        eventDate = date
    }

}