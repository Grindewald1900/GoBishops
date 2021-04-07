package com.example.gobishops.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.entity.Schedule
import com.joooonho.SelectableRoundedImageView


/**
 * Created by Yee on 2021/2/27.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ScheduleCardAdapter(var schedules: ArrayList<Schedule>): RecyclerView.Adapter<ScheduleCardAdapter.ScheduleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_schedule,parent, false)
        return ScheduleHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        val schedule: Schedule = schedules[position]
        // This is the content
        if(schedule.dateType == 1){
            holder.layoutContent.visibility = View.VISIBLE
            holder.layoutMonth.visibility = View.GONE
            holder.layoutYear.visibility = View.GONE
            holder.tvDate.text = schedule.day.toString()
            holder.tvTitle.text = schedule.title
            Glide.with(holder.itemView)
                .asBitmap()
                .load(R.drawable.img_portrait1)
                .into(holder.ivPortrait)
        }
        // This is the month
        if (schedules[position].dateType == 2){
            holder.layoutContent.visibility = View.GONE
            holder.layoutMonth.visibility = View.VISIBLE
            holder.layoutYear.visibility = View.GONE
        }
        // This is the year
        if (schedules[position].dateType == 3){
            holder.layoutContent.visibility = View.GONE
            holder.layoutMonth.visibility = View.GONE
            holder.layoutYear.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    class ScheduleHolder(view: View) : RecyclerView.ViewHolder(view){
        var ivPortrait: SelectableRoundedImageView = view.findViewById(R.id.iv_schedule_portrait)
        var tvDate: TextView = view.findViewById(R.id.tv_schedule_date)
        var tvTitle: TextView = view.findViewById(R.id.tv_schedule_title)
        var tvAddition: TextView = view.findViewById(R.id.tv_schedule_additional_info)
        var ivIcon: ImageView = view.findViewById(R.id.iv_schedule_icon)

        var tvMonth: TextView = view.findViewById(R.id.tv_schedule_month)
        var tvYear: TextView = view.findViewById(R.id.tv_schedule_year)

        var layoutContent: ConstraintLayout = view.findViewById(R.id.layout_schedule_content)
        var layoutMonth: ConstraintLayout = view.findViewById(R.id.layout_schedule_month)
        var layoutYear: ConstraintLayout = view.findViewById(R.id.layout_schedule_year)

    }
}