package com.example.gobishops.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
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

    fun uploadImage(uri: Uri, mContext: Context){
        val uuid: String = UUID.randomUUID().toString()
        val reference = mStorageRef.child(ConstantUtil.STORAGE_IMAGE + uuid)
//        val file: Uri = Uri.fromFile(File("path/to/images/rivers.jpg"))
        Log.d("Storage", "uploadImage")

        reference.putFile(uri)
            .addOnSuccessListener { taskSnapshot -> // Get a URL to the uploaded content
                Log.d("Storage", "addOnSuccessListener")
//                val downloadUrl: Uri = taskSnapshot.getDownloadUrl()
            }
            .addOnFailureListener {
                Log.d("Storage", "addOnFailureListener")
                // Handle unsuccessful uploads

            }
            .addOnProgressListener {
                val percentage = (it.bytesTransferred / it.totalByteCount) * 100
                if (percentage.toInt() % 10 == 0){
                    Toast.makeText(mContext, "Uploading : ${percentage}%", Toast.LENGTH_SHORT).show()
                }
            }
    }

}