package com.example.gobishops.utils

import android.R.attr
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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
                    Log.d("mAuth", "createUserWithEmail:fail" + it.exception)
                }
            }
            .addOnFailureListener {
                Log.d("mAuth", "OnFailureListener $it")
            }
            .addOnCanceledListener {
                Log.d("mAuth", "OnCanceledListener")
            }
    }

    /**
     * Implemented with Firebase Authentication
     * @param email: email address
     * @param password: user password
     */
    fun signInAccount(email: String, password: String, mActivity: Activity){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(mActivity,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("mAuth", "signInWithEmail:success")
                        val user = mAuth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("mAuth", "signInWithEmail:failure", task.exception)
                        Toast.makeText(mActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }

                    // ...
                })
    }



}

