package com.example.gobishops.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.entity.NormalCard
import com.joooonho.SelectableRoundedImageView


/**
 * Created by Yee on 2021/2/3.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class NormalCardAdapter(var date: ArrayList<NormalCard>): RecyclerView.Adapter<NormalCardAdapter.NormalCardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalCardHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_normal_card, parent, false)
        return NormalCardHolder(itemView)
    }

    override fun onBindViewHolder(holder: NormalCardHolder, position: Int) {
        val cardData = date[position]
        holder.title.text = cardData.title
        holder.location.text = cardData.location
        holder.time.text = cardData.time
        Glide.with(holder.itemView)
            .asBitmap()
            .load(R.drawable.img_portrait)
            .into(holder.portrait)
    }

    override fun getItemCount(): Int {
        return date.size
    }
    class NormalCardHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.tv_normal_card_title)
        var location: TextView = view.findViewById(R.id.tv_normal_card_location)
        val time: TextView = view.findViewById(R.id.tv_normal_card_time)
        var portrait: SelectableRoundedImageView = view.findViewById(R.id.iv_normal_card_portrait)
        val background: ImageView = view.findViewById(R.id.iv_normal_card_image)
    }
}