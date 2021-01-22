package com.example.gobishops.utils

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.gobishops.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.net.URI
import java.util.*


/**
 * Created by Yee on 2021/1/16.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object StorageUtil {
    private val mStorageRef: StorageReference = FirebaseStorage.getInstance().reference

    fun uploadImage(uri: Uri, imageId: String, mContext: Context){
        val uuid: String = UUID.randomUUID().toString()
        val reference = mStorageRef.child(ConstantUtil.STORAGE_IMAGE + imageId)
        Log.d("Storage", "uploadImage")

        reference.putFile(uri)
            .addOnSuccessListener { taskSnapshot -> // Get a URI to the uploaded content
                Toast.makeText(App.mContext, Resources.getSystem().getString(R.string.upload_success), Toast.LENGTH_SHORT).show()
//                val downloadUrl: Uri = taskSnapshot.getDownloadUrl()
            }
            // Handle unsuccessful uploads
            .addOnFailureListener {
                Toast.makeText(App.mContext, Resources.getSystem().getString(R.string.upload_fail), Toast.LENGTH_SHORT).show()

            }
            .addOnProgressListener {
                val percentage = (it.bytesTransferred / it.totalByteCount) * 100
                if (percentage.toInt() % 10 == 0){
                    Toast.makeText(mContext, "Uploading : ${percentage}%", Toast.LENGTH_SHORT).show()
                }
            }
    }

}