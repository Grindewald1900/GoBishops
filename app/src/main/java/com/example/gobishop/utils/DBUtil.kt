package com.example.gobishop.utils

import android.util.Log
import com.example.gobishop.entity.User
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


/**
 * Created by Yee on 2021/1/8.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object DBUtil {
//    private lateinit var databaseRef: DatabaseReference
    fun setValue(){
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val rootRef = database.getReference()
        Log.d("DatabaseUtil", "Database init...")
        Log.d("DatabaseUtil", rootRef.toString())
        rootRef.setValue("Hello, World!")
//            .addOnSuccessListener {
//                Log.d("DatabaseUtil", "Success")
//            }.addOnFailureListener {
//                Log.d("DatabaseUtil", it.message.toString())
//            }
    //        val userTest = User("Grindewald", "hhah@gmail.com")

    }
}