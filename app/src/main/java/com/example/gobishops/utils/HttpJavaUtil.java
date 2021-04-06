package com.example.gobishops.utils;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;

/**
 * Created by Yee on 2021/4/1.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
public class HttpJavaUtil {
    /**
     * LoginByPost: Login with HTTP POST
     * @param name: user name
     * @param password: user password
     * @return: JSONArray in a string (user info)
     */
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


    /**
     * RegisterByPost: Register with HTTP POST
     * @param id: user id
     * @param name: user name
     * @param password: user password
     * @param email: user email
     * @return: if successful(bool) in a string
     */
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

    /**
     * AddOrderByPost: Add order with HTTP POST
     * @param sequenceId: Sequence number of the item in a order. Since and order may contain several items.
     * @param orderId: Order id
     * @param userId: User id
     * @param storeId: Store id
     * @param itemId: Item id
     * @param amount: Amount
     * @return: Result
     */
    public static String OrderByPost(int sequenceId, int orderId, int userId, int storeId, int itemId, int amount, int daoType){
        String address = ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_ADD_ORDER;
        String result = "";
        Log.d("AddOrderByPost", "address" + address);

        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //Set up timeout
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "&id="+ URLEncoder.encode(String.valueOf(sequenceId),"UTF-8")+
                    "&order_id="+ URLEncoder.encode(String.valueOf(orderId),"UTF-8")+
                    "&user_id="+ URLEncoder.encode(String.valueOf(userId),"UTF-8")+
                    "&store_id="+ URLEncoder.encode(String.valueOf(storeId),"UTF-8")+
                    "&item_id="+ URLEncoder.encode(String.valueOf(itemId),"UTF-8")+
                    "&item_amount="+ URLEncoder.encode(String.valueOf(amount),"UTF-8")+
                    // dao_type - 1 : save order 2 - : search order
                    "&dao_type="+ URLEncoder.encode(String.valueOf(daoType),"UTF-8");

            OutputStream out = conn.getOutputStream();

            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();
            Log.d("AddOrderByPost", "getResponseCode" + conn.getResponseCode());

            if (conn.getResponseCode() == 200) {
                Log.d("AddOrderByPost","getResponseCode 200");
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
                Log.d("AddOrderByPost", "result" + result);

                return result;
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * GetDish: get dish list from server
     * @param id: if -1, request for all dishes, if positive id, request for dishes of certain restaurant
     * @return: JSONArray in a string (dish info)
     */
    public static String GetDishByPost(int id){
        String address = ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_DISH;
        String result = "";
        Log.d("AddOrderByPost", address);

        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //Set up timeout
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);
            // request for dishes by id
            String data = "&id="+ URLEncoder.encode(String.valueOf(id),"UTF-8");
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
            Log.d("AddOrderByPost - e",e.getMessage());
        }
        return result;
    }

    /**
     * GetDish: get store list from server
     * @return: JSONArray in a string (dish info)
     */
    public static String GetStoreByPost(int id, int daoType){
        String address = ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_STORE;
        String result = "";

        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //Set up timeout
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "&id="+ URLEncoder.encode(String.valueOf(id),"UTF-8")+
                    "&dao_type="+ URLEncoder.encode(String.valueOf(daoType),"UTF-8");

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
            Log.d("AddOrderByPost - e",e.getMessage());
        }
        return result;
    }

    /**
     * EventByPost: add event or get a list of event
     * @return: JSONArray in a string (dish info)
     */
    public static String EventByPost(int eventId, int userId, int storeId, String eventType, int daoType){
        String address = ConstantUtil.SERVER_URL + ConstantUtil.SERVLET_EVENT;
        String result = "";

        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //Set up timeout
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);

            String data = "&event_id="+ URLEncoder.encode(String.valueOf(eventId),"UTF-8")+
                    "&user_id="+ URLEncoder.encode(String.valueOf(userId),"UTF-8")+
                    "&store_id="+ URLEncoder.encode(String.valueOf(storeId),"UTF-8")+
                    "&event_type="+ URLEncoder.encode(eventType,"UTF-8")+
                    "&dao_type="+ URLEncoder.encode(String.valueOf(daoType),"UTF-8");

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
            Log.d("AddOrderByPost - e",e.getMessage());
        }
        return result;
    }

}
