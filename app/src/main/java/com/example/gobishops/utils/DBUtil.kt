package com.example.gobishops.utils

import android.util.Log
import com.google.firebase.database.FirebaseDatabase



/**
 * Created by Yee on 2021/1/8.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object DBUtil {
    /**
     * Implemented with Firebase Realtime database
     * @param path: the path based on root reference, e.g. Users/user-000001
     * @param content: any kind of object need to upload, e.g. User.class
     */
    fun addEntity(path: String, content: Any){
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference(path)
        Log.d("DatabaseUtil", "Database init...")
        Log.d("DatabaseUtil", reference.toString())
        reference.setValue(content)
            .addOnCompleteListener {
                Log.d("DatabaseUtil", "Complete")
            }.addOnSuccessListener {
                Log.d("DatabaseUtil", "Success")
            }.addOnFailureListener {
                Log.d("DatabaseUtil", it.message.toString())
            }.addOnCanceledListener {
                Log.d("DatabaseUtil", "Canceled")
            }
//        rootRef.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d("DatabaseUtil", "onDataChange")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("DatabaseUtil", "onCancelled")
//            }
//        })
    }
}