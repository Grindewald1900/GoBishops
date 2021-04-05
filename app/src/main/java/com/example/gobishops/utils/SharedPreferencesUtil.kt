package com.example.gobishops.utils

import android.content.SharedPreferences
import com.example.gobishops.entity.OrderItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Yee on 2021/3/30.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object SharedPreferencesUtil {
    private var mPrefs: SharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(App.mContext)
    private var orders: ArrayList<OrderItem> = ArrayList()
    private var orderIds: ArrayList<Int> = ArrayList()
    private val prefsEditor: SharedPreferences.Editor = mPrefs.edit()
    private val type = object : TypeToken<ArrayList<OrderItem?>?>() {}.type

    init {
        if(!mPrefs.getString(ConstantUtil.CLASS_ORDER_ITEM, "").isNullOrEmpty()){
            orders = Gson().fromJson(mPrefs.getString(ConstantUtil.CLASS_ORDER_ITEM, ""), type)
            orders.forEach {
                orderIds.add(it.item!!.id)
            }
        }
    }


    /**
     *  Add a unique order to order list
     */
    fun saveOrder(order: OrderItem){
        // Here we keep the order data with an order list and an orderId list
        // If orderId exists, update the order accordingly
        if (orderIds.contains(order.item!!.id)){
            val index = orderIds.indexOf(order.item.id)
            orders[index] = order
        }else{
            orders.add(order)
            orderIds.add(order.item.id)
        }
        val json = Gson().toJson(orders)

        // Save the key as ORDER_ITEM + Class name
        prefsEditor.putString(ConstantUtil.CLASS_ORDER_ITEM, json).apply()
    }

    /**
     * Update the count of order
     */
    fun updateOrder(order: OrderItem){
        if (orderIds.contains(order.item!!.id)){
            val index = orderIds.indexOf(order.item.id)
            orders[index] = order
        }
        val json = Gson().toJson(orders)

        // Save the key as ORDER_ITEM
        prefsEditor.putString(ConstantUtil.CLASS_ORDER_ITEM, json).apply()
    }


    /**
     * Search and return the whole order list
     */
    fun getOrder(key: String): ArrayList<OrderItem>{
        val json = mPrefs.getString(key, "")
        return Gson().fromJson(json, type)
    }


    /**
     * Remove a certain order, actually by its id
     */
    fun removeOrder(order: OrderItem){
        if (orderIds.contains(order.item!!.id)){
            val index = orderIds.indexOf(order.item.id)
            orders.removeAt(index)
            orderIds.removeAt(index)
        }
        val json = Gson().toJson(orders)

        // Save the key as ORDER_ITEM + Class name
        prefsEditor.putString(ConstantUtil.CLASS_ORDER_ITEM, json).apply()
    }

    fun releaseOrder(){
        orders.clear()
        orderIds.clear()
        val json = Gson().toJson(orders)
        prefsEditor.putString(ConstantUtil.CLASS_ORDER_ITEM, json).apply()
    }

    /**
     *  Return the static reference of SharedPreferences
     */
    fun getPreference(): SharedPreferences {
        return mPrefs
    }

    /**
     *  If the order list has been initialized
     */
    fun isSharedKeyEmpty(key: String): Boolean{
        return mPrefs.getString(key,"").isNullOrEmpty()
    }

}