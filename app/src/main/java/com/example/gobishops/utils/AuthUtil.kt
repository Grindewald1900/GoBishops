package com.example.gobishops.utils

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.gobishops.contract.BaseContract
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ActionCodeSettings
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

    /**
     * If user have signed in
     * @return : true if signed in.
     */
    fun isSignedIn(): Boolean{
        if (null != mAuth.currentUser) return true
        return false
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
    fun signInAccount(
        email: String,
        password: String,
        mActivity: Activity,
        view: BaseContract.OnDataRetrieved
    ){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(mActivity,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("mAuth", "signInWithEmail:success")
                        val user = mAuth.currentUser
//                        view.getRetrievedData(ConstantUtil.STATE_SUCCESS, user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("mAuth", "signInWithEmail:failure", task.exception)
                        Toast.makeText(mActivity, "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()
//                        view.getRetrievedData(ConstantUtil.STATE_FAIL, task.exception)
                    }

                    // ...
                })
    }

    /**
     * Implemented with Firebase Authentication
     * @param email:user provide this email to receive the verification code
     */
    fun sendSignInEmail(email: String){
        Log.d("mAuth", "sendSignInEmail")
        val actionCodeSettings = ActionCodeSettings.newBuilder() // URL you want to redirect back to. The domain (www.example.com) for this
                // URL must be whitelisted in the Firebase Console.
                .setUrl("https://gobishops.page.link/forgetPwd") // This must be true
                .setHandleCodeInApp(true)
                .setIOSBundleId("com.example.ios")
                .setAndroidPackageName(
                    "com.example.gobishops",
                    true,  /* installIfNotAvailable */
                    "12" /* minimumVersion */
                )
                .build()
        mAuth.sendSignInLinkToEmail(email, actionCodeSettings).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("mAuth", "Email sent")
            }
            if(it.isCanceled){
                Log.d("mAuth", "Email canceled")
            }
            if(it.isComplete){
                Log.d("mAuth", "Email isComplete")
            }
        }.addOnFailureListener {
            Log.d("mAuth", it.toString())
        }

    }



}

