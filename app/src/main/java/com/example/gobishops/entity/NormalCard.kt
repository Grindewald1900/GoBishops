package com.example.gobishops.entity

import android.net.Uri
import com.example.gobishops.utils.ConstantUtil


/**
 * Created by Yee on 2021/2/3.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
data class NormalCard (val userName: String,
                       val title: String,
                       val location: String,
                       val time: String,
                       val  color: Int,
                       val portraitUri: Uri?,
                       val imageUri: Uri?){
    constructor(): this("Grindewald1900", "This is title", "Oxford", "2021", ConstantUtil.USER_STUDENT, null, null)
}