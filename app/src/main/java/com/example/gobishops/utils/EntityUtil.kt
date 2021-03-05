package com.example.gobishops.utils

import com.example.gobishops.entity.Event
import com.example.gobishops.entity.Schedule


/**
 * Created by Yee on 2021/3/4.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object EntityUtil {
    fun eventToSchedule(event: Event, sType: Int, dType: Int): Schedule{
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