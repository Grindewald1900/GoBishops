package com.example.gobishops.utils


/**
 * Created by Yee on 2021/1/15.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object ConstantUtil {
    // URL
    const val DATABASE_USER: String = "Users"
    const val DATABASE_EVENT: String = "Events/"
    const val STORAGE_IMAGE: String = "Images/"

    // Classes
    const val CLASS_ITEM: String = "ITEM"
    const val CLASS_ORDER_ITEM = "ORDER_ITEM"

    // Ordinary String
    const val STRING_RESULT_ACTIVITY = "RESULT_ACTIVITY"

    // Connection - Local test
    const val SERVER_URL: String = "http://10.0.2.2:8080/BU_war_exploded/"
    const val SERVLET_REGISTER: String = "Servlet.RegisterServlet.do"
    const val SERVLET_LOGIN: String = "Servlet.LoginServlet.do"
    const val SERVLET_ADD_ORDER: String = "Servlet.OrderServlet.do"
    const val SERVLET_DISH: String = "Servlet.DishServlet.do"



    const val SERVER_RESULT = "result"
    const val SERVER_SUCCESS = "success"
    const val SERVER_FAIL = "fail"

    //

    // States
    const val STATE_SUCCESS = 1
    const val STATE_FAIL = 2
    const val STATE_CANCEL = 3

    // onActivityResult
    const val SELECT_IMAGE: Int = 101

    // ResultActivity
    const val RESULT_DEFAULT : Int = 500
    const val RESULT_CORRECT : Int = 501
    const val RESULT_INCORRECT : Int = 502


    // User type
    const val USER_STUDENT: Int = 201
    const val USER_PROFESSOR: Int = 202

    // Handler
    const val HANDLER_LOGIN: Int = 301
    const val HANDLER_REGISTER: Int = 302
    const val HANDLER_ORDER: Int = 303
    const val HANDLER_DISH: Int = 304


    // Constant amount
    const val MAX_UPLOAD_IMAGE: Int = 5
    const val MAX_SEARCH_HISTORY: Int = 5
    const val MIN_TRANSFORM_SCALE = 0.8f
    const val MIN_TRANSFORM_ALPHA = 0.5f


}