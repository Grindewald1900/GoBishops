package com.example.gobishops.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.entity.Event
import com.joooonho.SelectableRoundedImageView


/**
 * Created by Yee on 2021/2/27.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ScheduleCardAdapter(var schedule: ArrayList<Event>): RecyclerView.Adapter<ScheduleCardAdapter.ScheduleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_schedule,parent, false)
        return ScheduleHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        Glide.with(holder.itemView)
            .asBitmap()
            .load(R.drawable.img_portrait)
            .into(holder.portrait)
    }

    override fun getItemCount(): Int {
        return schedule.size
    }

    class ScheduleHolder(view: View) : RecyclerView.ViewHolder(view){
        var portrait: SelectableRoundedImageView = view.findViewById(R.id.iv_schedule_portrait)
    }
}