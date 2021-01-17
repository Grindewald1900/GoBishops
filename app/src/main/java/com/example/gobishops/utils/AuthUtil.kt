package com.example.gobishops.utils

import android.util.Log
import com.example.gobishops.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


/**
 * Created by Yee on 2021/1/15.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object AuthUtil {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun getCurrentUser(): FirebaseUser?{
        return mAuth.currentUser
    }

    /**
     * Implemented with Firebase Authentication
     * @param email: email address
     * @param password: user password
     */
    fun createAccount(email: String, password: String){
        Log.d("mAuth", "createUserWithEmail")
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    // Sign in success,
                    Log.d("mAuth", "createUserWithEmail:success")
                }else{
                    // Sign in fails
                    Log.d("mAuth", "createUserWithEmail:fail"+it.exception)
                }
            }
            .addOnFailureListener {
                Log.d("mAuth", "OnFailureListener $it")
            }
            .addOnCanceledListener {
                Log.d("mAuth", "OnCanceledListener")
            }
    }



}