package com.example.gobishops.utils

import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import com.example.gobishops.R
import com.example.gobishops.contract.BaseContract
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


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
        val reference = database.reference.child(path)
        Log.d("DatabaseUtil", "Database init...")
        Log.d("DatabaseUtil", reference.toString())
        reference.setValue(content)
            .addOnCompleteListener {
                Log.d("DatabaseUtil", "Complete")
            }.addOnSuccessListener {
                Toast.makeText(App.mContext, App.mContext?.getString(R.string.upload_success), Toast.LENGTH_SHORT).show()
                Log.d("DatabaseUtil", "Success")
            }.addOnFailureListener {
                Toast.makeText(App.mContext, App.mContext?.getString(R.string.upload_fail), Toast.LENGTH_SHORT).show()
                Log.d("DatabaseUtil", it.message.toString())
            }.addOnCanceledListener {
                Toast.makeText(App.mContext, App.mContext?.getString(R.string.upload_cancel), Toast.LENGTH_SHORT).show()
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

    fun getEntity(path: String, content: Any, view: BaseContract.OnDataRetrieved){
        val reference = FirebaseDatabase.getInstance().reference.child(path)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue()
                view.getRetrievedData(ConstantUtil.STATE_SUCCESS, data)
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(App.mContext, Resources.getSystem().getString(R.string.upload_fail), Toast.LENGTH_SHORT).show()
                // Getting Post failed, log a message
                // ...
            }
        })
    }
}