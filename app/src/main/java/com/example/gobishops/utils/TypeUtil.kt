package com.example.gobishops.utils

import com.example.gobishops.entity.Event


/**
 * Created by Yee on 2021/1/22.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object TypeUtil {
    fun HashMapToEvent(map: HashMap<*,*>): ArrayList<Event>{
        val events: ArrayList<Event> = ArrayList()
        for ((key, value) in map){
            val eventItem = Event()
            val data: HashMap<*,*> = value as HashMap<*, *>
            eventItem.id = data.get("id") as String
            eventItem.additionalInfo = data.get("additionalInfo") as String
            eventItem.day = (data.get("day") as Long).toInt()
            eventItem.month = (data.get("month") as Long).toInt()
            eventItem.year = (data.get("year") as Long).toInt()
            eventItem.type = (data.get("type") as Long).toInt()
            eventItem.permission = (data.get("permission") as Long).toInt()
            eventItem.descriptor = data.get("descriptor") as String
            if(null != data.get("invitedUser")){
                //TODO: Do not know the type of this value(value.get("invitedUser")), mat be mismatched.
                eventItem.invitedUser = data.get("invitedUser")!! as ArrayList<String>
            }
            eventItem.location = data.get("location") as String
            eventItem.participants = data.get("participants") as String
            eventItem.title = data.get("title") as String
            events.add(eventItem)
        }
        return events
    }
}