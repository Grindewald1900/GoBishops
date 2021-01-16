package com.example.gobishops.utils

import android.util.Log
import com.google.firebase.database.FirebaseDatabase



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