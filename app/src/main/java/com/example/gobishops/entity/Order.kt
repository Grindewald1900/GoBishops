package com.example.gobishops.entity


/**
 * Created by Yee on 2021/3/28.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 *
 * @param id: order id
 * @param userId: id of user who place the order
 * @param score_env: restaurant atmosphere, decor style.. from 1 to 5
 * @param score_taste: is the food delicious or not, from 1 to 5
 * @param score_service: how about service? from 1 to 5
 */
data class Order(
    val id: Int,
    val userName: String,
    val userId: Int,
    val date: String,
    val price: Float,
    val tel: String,
    val content: String,
    val score_taste: Int,
    val score_env: Int,
    val score_service: Int,
) {

}

//enum class Score{1, 2, 3, 4, 5}