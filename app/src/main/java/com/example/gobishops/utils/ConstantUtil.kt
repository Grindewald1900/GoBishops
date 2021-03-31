package com.example.gobishops.utils


/**
 * Created by Yee on 2021/1/15.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object ConstantUtil {
    //URL
    const val DATABASE_USER: String = "Users"
    const val DATABASE_EVENT: String = "Events/"
    const val STORAGE_IMAGE: String = "Images/"

    //Classes
    const val CLASS_ITEM: String = "ITEM"
    const val CLASS_ORDER_ITEM = "ORDER_ITEM"

    // States
    const val STATE_SUCCESS = 1
    const val STATE_FAIL = 2
    const val STATE_CANCEL = 3

    // onActivityResult
    const val SELECT_IMAGE: Int = 101

    // User type
    const val USER_STUDENT: Int = 201
    const val USER_PROFESSOR: Int = 202

    // Constant amount
    const val MAX_UPLOAD_IMAGE: Int = 5
    const val MAX_SEARCH_HISTORY: Int = 5
    const val MIN_TRANSFORM_SCALE = 0.8f
    const val MIN_TRANSFORM_ALPHA = 0.5f


}