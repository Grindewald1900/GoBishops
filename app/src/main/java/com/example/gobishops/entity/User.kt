package com.example.gobishops.entity

import java.util.*


/**
 * Created by Yee on 2021/1/9.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * @param id: user id
 * @param userName: name of user
 * @param email
 * @param birthday: date of birth, Date class
 * @param gender: 'M' or 'F'
 * @param phoneNumber
 * @param image: user portrait image
 */
data class User(
    val id: Int,
    var userName: String,
    var email: String,
    val birthday: Date,
    val gender: Char,
    val phoneNumber: String,
    val image: String,
){
    constructor(): this(10000, "Alili" , "alili@ubishops.ca", Date(1900, 2,2), 'M', "191919191", "")
}
