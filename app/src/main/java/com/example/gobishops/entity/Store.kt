package com.example.gobishops.entity

import java.io.Serializable
import kotlin.random.Random


/**
 * Created by Yee on 2021/4/4.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * @param id: store id
 * @param name: store name
 * @param genre: store genre
 * @param averagePrice: average price
 * @param tel: phone number
 * @param lat: latitude
 * @param long: longitude
 */
data class Store(
    val id: Int,
    val name: String,
    val address: String,
    val genre: String,
    val averagePrice: Float,
    val tel: String,
    val lat: Float,
    val long: Float,
    var isCollect: Boolean,
): Serializable {
    constructor():
            this((1..10000).random(), "Papa's Cafe", "College Street", "Fast Food", 15f,"+18195635828", 45.36750783294007f, -71.85578947857024f, false)
    constructor(id: Int, name: String, address: String, genre: String, averagePrice: Float, tel: String, lat: Float, long: Float):
            this(id, name, address, genre, averagePrice, tel, lat, long, false)

}