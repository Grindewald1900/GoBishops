package com.example.gobishops.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Yee on 2021/4/1.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
public class HttpJavaUtil {
    public static String LoginByPost(String name, String password){
        String address = ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_LOGIN;
        String result = "";
        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            // Request data
            String data = "name="+ URLEncoder.encode(name,"UTF-8")+
                    "&password="+ URLEncoder.encode(password,"UTF-8");

            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
                return result;
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String RegisterByPost(String id, String name, String password,String email){
        String address = ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_REGISTER;
        String result = "";

        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //Set up timeout
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            conn.setUseCaches(false);

            String data = "&id="+ URLEncoder.encode(id,"UTF-8")+
                    "&name="+ URLEncoder.encode(name,"UTF-8")+
                    "&password="+ URLEncoder.encode(password,"UTF-8")+
                    "&email="+ URLEncoder.encode(email,"UTF-8");

            OutputStream out = conn.getOutputStream();

            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                result = new String(message.toByteArray());
                return result;
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return result;

    }

}
