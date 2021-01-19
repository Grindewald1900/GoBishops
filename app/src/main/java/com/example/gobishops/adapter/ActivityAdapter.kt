package com.example.gobishops.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gobishops.R
import com.example.gobishops.view.EventDetailActivity
import com.example.gobishops.entity.BuActivity


/**
 * Created by Yee on 2021/1/3.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ActivityAdapter(var activities: ArrayList<BuActivity>): RecyclerView.Adapter<ActivityAdapter.ActivityHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_recycle_activity, parent, false)
        return ActivityHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActivityHolder, position: Int) {
        val activity = activities.get(position)
        holder.title.text = activity.title
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    class ActivityHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.tv_item_activity_title)
        var subtitle: TextView = view.findViewById(R.id.tv_item_activity_subtitle)
        var content: TextView = view.findViewById(R.id.tv_item_activity_content)
        init{
            // Set listener for each element in the activity item
            content.setOnClickListener {
                Toast.makeText(it.context, "Content Clicked", Toast.LENGTH_SHORT).show()
                Log.v("tag_activity", "Item Click")
                var intent = Intent(it.context, EventDetailActivity::class.java)
                it.context.startActivity(intent)

            }
            subtitle.setOnClickListener {
                Toast.makeText(it.context, "Subtitle Clicked", Toast.LENGTH_SHORT).show()
                Log.v("tag_activity", "Item Click")
            }
            title.setOnClickListener {
                Toast.makeText(it.context, "Title Clicked", Toast.LENGTH_SHORT).show()
                Log.v("tag_activity", "Item Click")
            }
        }

        companion object{
            private val ACTIVITY_KEY = "ACTIVITY"
        }
    }
}