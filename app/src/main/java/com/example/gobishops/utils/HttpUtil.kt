package com.example.gobishops.utils

import android.R.attr
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder


/**
 * Created by Yee on 2021/4/1.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
object HttpUtil {
    var isSignedIn: Boolean = false

    fun registerByPost(id: Int, name: String, password: String, email: String, img: ByteArrayOutputStream): String{
        var result = ""

        try {
            val url = URL(ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_REGISTER)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"

            //Set up timeout
            conn.readTimeout = 5000
            conn.connectTimeout = 5000
            conn.useCaches = false
            val data: String = "id=" + id.toString() + "name=" + name + "&password=" + URLEncoder.encode(password, "UTF-8").toString() +
                    "&email=" + URLEncoder.encode(email, "UTF-8")
            val out: OutputStream = conn.outputStream
            out.write(data.toByteArray())
            out.flush()
            out.close()
            conn.connect()

            if (conn.responseCode == 200) {
                val inputStream: InputStream = conn.inputStream
                val message = ByteArrayOutputStream()
                var len = 0
                val buffer = ByteArray(1024)
                while (inputStream.read(buffer).also { len = it } != -1) {
                    message.write(buffer, 0, len)
                }
                inputStream.close()
                message.close()
                result = String(message.toByteArray())
                //return result;
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result

    }
}