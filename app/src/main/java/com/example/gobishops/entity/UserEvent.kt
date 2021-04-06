package com.example.gobishops.entity


/**
 * Created by Yee on 2021/4/6.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * @param eventId: event id
 * @param userId: user id
 * @param storeId: store id
 * @param eventType: event type
 */
data class UserEvent(
    val eventId: Int,
    val userId: Int,
    val storeId: Int,
    val eventType: String
)
