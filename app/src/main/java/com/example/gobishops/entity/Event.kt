package com.example.gobishops.entity

import java.io.FileDescriptor
import java.time.LocalDate
/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * @param id: Event Id (Mandatory)
 * @param title: Event title (Mandatory)
 * @param location: Location of event
 * @param date: Date of event, e.g.  2007-12-03 (Mandatory)
 * @param descriptor: Some description of the event
 * @param type: Type of event (Mandatory)
 * @param participants: Describe who can join the event
 * @param permission: Permission level of event (Mandatory)
 * @param invitedUser: A list of string, including the IDs of invited users
 * @param additionalInfo: Some additional information
 * @param imageSet: A list of string, including the IDs of images to be uploaded.
 */
data class Event(
    var id: String,
    var title: String,
    var location: String,
    var year: Int,
    var month: Int,
    var day: Int,
    var descriptor: String,
    var type: Int,
    var participants: String,
    var permission: Int,
    var invitedUser: ArrayList<String>,
    var additionalInfo: String,
    var imageSet: ArrayList<String> = ArrayList()
)