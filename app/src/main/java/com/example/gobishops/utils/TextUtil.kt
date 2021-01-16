package com.example.gobishops.utils


/**
 * Created by Yee on 2020/12/30.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class TextUtil {
    companion object{
        fun getCharIndex(string: String, target: String): ArrayList<Int>{
            var indexes: ArrayList<Int> = ArrayList()
            var tempIndex: Int = 0
            indexes.add(0)
            while (tempIndex != -1){
                tempIndex = string.indexOf(target, tempIndex)
                if(tempIndex != -1){
                    indexes.add(tempIndex)
                    tempIndex++
                }
            }
            return indexes
        }
    }
}