package com.example.gobishops.adapter

import android.Manifest.permission.*
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gobishops.R
import com.example.gobishops.entity.Order
import com.example.gobishops.myview.mButtonView
import com.example.gobishops.utils.TextUtil
import com.example.gobishops.view.OrderActivity
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


/**
 * Created by Yee on 2021/1/3.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

class EventAdapter(var orders: ArrayList<Order>): RecyclerView.Adapter<EventAdapter.ActivityHolder>(){
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.view_recycle_event,
            parent,
            false
        )
        mContext = parent.context
        return ActivityHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActivityHolder, position: Int) {
        val order = orders[position]
        holder.title.text = TextUtil.getOrderId(order.id)
        holder.subtitle.text = TextUtil.getItemPrice(order.price)
        holder.content.text = order.content
        holder.addtional.text = "Placed by ${order.userName} at ${order.date}"
        holder.contact.setOnClickListener {
            Toast.makeText(mContext, "Click", Toast.LENGTH_SHORT).show()
            AndPermission.with(mContext)
                .runtime()
                .permission(Permission.CALL_PHONE)
                .onGranted {
                    mContext.startActivity(
                        Intent(
                            Intent.ACTION_CALL,
                            Uri.parse("tel:" + order.tel)
                        )
                    )
                }
                .onDenied {
                    Toast.makeText(mContext, R.string.hint_no_perm, Toast.LENGTH_SHORT).show()
                }
                .start()

        }
        holder.layout.setOnClickListener {
            it.context.startActivity(Intent(it.context, OrderActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class ActivityHolder(view: View): RecyclerView.ViewHolder(view){
        var layout: ConstraintLayout = view.findViewById(R.id.view_event)
        var title: TextView = view.findViewById(R.id.tv_item_activity_title)
        var subtitle: TextView = view.findViewById(R.id.tv_item_activity_subtitle)
        var content: TextView = view.findViewById(R.id.tv_item_activity_content)
        var addtional: TextView = view.findViewById(R.id.tv_item_activity_info)
        var contact: mButtonView = view.findViewById(R.id.btn_item_activity_contact)

        init{
            // Set listener for each element in the activity item
        }

        companion object{
            private val ACTIVITY_KEY = "ACTIVITY"
        }
    }
}