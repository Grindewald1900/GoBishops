package com.example.gobishops.model

import com.example.gobishops.contract.AddEventContract


/**
 * Created by Yee on 2021/1/19.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class AddEventModel: AddEventContract.Model {
    private var uploadCounter: Int = 0

    override fun getUploadCounter() = uploadCounter

    override fun addUploadCounter() {
        uploadCounter++
    }

}