package com.example.gobishops.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.entity.Contact
import com.example.gobishops.utils.ImageUtil


/**
 * Created by Yee on 2021/4/6.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * Implemented for listview in friend list(Group fragment)
 */
class ContactAdapter(activity: Activity, private val resourceId:Int, data:List<Contact>): ArrayAdapter<Contact>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view : View = convertView ?: LayoutInflater.from(context).inflate(resourceId, parent, false)
        var contactImg : ImageView = view.findViewById(R.id.btn_view_contact_portrait)
        val contactName : TextView = view.findViewById(R.id.tv_view_contact_name)
        val contactMsg: TextView = view.findViewById(R.id.tv_view_contact_message)
        val contact = getItem(position)
        Glide.with(parent.context).asBitmap().load(ImageUtil.getRandomPortrait(parent.context)).into(view.findViewById(R.id.btn_view_contact_portrait))
        if(null != contact){
            contactName.text = contact.name
        }
        return view
    }
}