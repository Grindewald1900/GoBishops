package com.example.gobishops.adapter

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.gobishops.R
import com.example.gobishops.entity.ChatMessage
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.ImageUtil
import com.example.gobishops.utils.LoginStateUtil
import com.joooonho.SelectableRoundedImageView


/**
 * Created by Yee on 2021/4/6.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

/**
 * Implemented for listview in Chat activity(chat detail)
 */
class ChatAdapter(activity: Activity, private val resourceId:Int, data:List<ChatMessage>): ArrayAdapter<ChatMessage>(activity, resourceId, data) {
    /** Here we use a random value to yield the user portrait**/
    private var imageId: Int? = ImageUtil.getRandomPortrait(activity)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // getRandomPortrait() may return null value
        if(null == imageId) imageId = R.drawable.img_portrait1

        // Initialize
        val view : View = convertView ?: LayoutInflater.from(context).inflate(resourceId, parent, false)
        val imageLeft : SelectableRoundedImageView = view.findViewById(R.id.iv_chat_portrait_left)
        val imageRight : SelectableRoundedImageView = view.findViewById(R.id.iv_chat_portrait_right)
        val chatMsg : TextView = view.findViewById(R.id.tv_chat_message)
        val chatTime : TextView = view.findViewById(R.id.tv_chat_time)
        val chat = getItem(position)
        if(null != chat){
            when {
                // System message
                chat.msg.contains(ConstantUtil.STRING_CHAT_SYSTEM_INIT) -> {
                    imageLeft.visibility = View.INVISIBLE
                    imageRight.visibility = View.INVISIBLE
                    chatMsg.textSize = 24f
                    chatMsg.setTextColor(Color.parseColor("#7926A9"))
                }
                // User message
                chat.userName.contains(LoginStateUtil.getUser()!!.userName) -> {
                    imageLeft.visibility = View.INVISIBLE
                    imageRight.background = parent.context.getDrawable(R.drawable.img_portrait1)
                    chatMsg.gravity = Gravity.END
                    chatTime.gravity = Gravity.END
                }
                // Friend message
                else -> {
                    imageRight.visibility = View.INVISIBLE
                    imageLeft.background = parent.context.getDrawable(imageId!!)
                    chatMsg.gravity = Gravity.START
                    chatTime.gravity = Gravity.START
                }
            }
            chatMsg.text = chat.msg
            chatTime.text = chat.time
        }
        return view
    }
}