package com.example.gobishops.utils

import com.example.gobishops.entity.Event
import com.example.gobishops.entity.Schedule


/**
 * Created by Yee on 2021/1/22.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object TypeUtil {
    /**
     * Convert hash map(from firebase) to Event
     */
    fun hashMapToEvent(map: HashMap<*,*>): ArrayList<Event>{
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

    /**
     * Convert Event to Schedule
     * @param event: the entity to be converted
     * @param sType: schedule type, 1-event, 2-deal, 3-reservation, 4-team
     * @param dType: 0-no type, 1-content, 2-month, 3-year
     */
    fun eventToSchedule(event: Event, sType: Int, dType: Int): Schedule {
        var schedule: Schedule = Schedule()
        schedule.additionalInfo = event.additionalInfo
        schedule.dateType = dType
        schedule.day = event.day
        schedule.descriptor = event.descriptor
        schedule.id = event.id
        schedule.imageSet = event.imageSet
        schedule.invitedUser = event.invitedUser
        schedule.location = event.location
        schedule.month = event.month
        schedule.participants = event.participants
        schedule.permission = event.permission
        schedule.scheduleType = sType
        schedule.title = event.title
        schedule.type = event.type
        schedule.userId = event.userId
        schedule.userType = event.userType
        schedule.year = event.year
        return schedule
    }
}