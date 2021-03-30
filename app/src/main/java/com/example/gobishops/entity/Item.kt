package com.example.gobishops.entity

import java.util.*
import kotlin.random.Random


/**
 * Created by Yee on 2021/3/28.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * @param id: item id
 * @param restaurantId: ID of the restaurant which provide this item
 * @param name: item name
 * @param price: item price
 * @param image: descriptive picture of the item(URI or Link)
 */
data class Item(
    val id: Int,
    val restaurantId: Int,
    val name: String,
    val price: Float,
    val image: String,
    val rate: Float,
) {
    constructor(): this(12345, 110, "taco", Random(100).nextFloat(), "Null", 4.5f)
}