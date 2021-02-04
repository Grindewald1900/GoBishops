package com.example.gobishops.adapter

import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gobishops.R
import com.example.gobishops.entity.Event
import com.example.gobishops.view.EventDetailActivity


/**
 * Created by Yee on 2021/1/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class EventResultAdapter (var events: ArrayList<Event>): RecyclerView.Adapter<EventResultAdapter.EventResultHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventResultHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_recycle_event_search, parent, false)
        return EventResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventResultHolder, position: Int) {
        val event = events[position]
        holder.title.text = event.title
        holder.info.text = event.year.toString() + event.month.toString() + event.day.toString() + event.location
        holder.permission.text = "Permission level: " + event.permission.toString()
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class EventResultHolder(view: View): RecyclerView.ViewHolder(view){
        var image: ImageView = view.findViewById(R.id.iv_item_search)
        var title: TextView = view.findViewById(R.id.tv_item_search_title)
        var info: TextView = view.findViewById(R.id.tv_item_search_info)
        var permission: TextView = view.findViewById(R.id.tv_item_search_permission)
        init {
            view.setOnClickListener {
                it.context.startActivity(Intent(it.context, EventDetailActivity::class.java))
            }
        }
    }
}