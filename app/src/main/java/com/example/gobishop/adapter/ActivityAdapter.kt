package com.example.gobishop.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Yee on 2021/1/3.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ActivityAdapter: RecyclerView.Adapter<ActivityAdapter.ActivityHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ActivityHolder?, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ActivityHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener{
        private var view: View = v
        private var title: TextView? = null
        init {
            v.setOnClickListener(this)
        }

        companion object{
            private val ACTIVITY_KEY = "ACTIVITY"
        }
        override fun onClick(v: View?) {
            Log.d("tag_activity", "Click")
        }

    }
}