package com.example.gobishops.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.entity.Item
import com.example.gobishops.entity.NormalCard
import com.example.gobishops.myview.mImageView
import com.example.gobishops.utils.App
import com.example.gobishops.utils.NumericUtil
import com.example.gobishops.utils.TextUtil
import com.example.gobishops.view.DishActivity
import com.example.gobishops.view.EventDetailActivity
import com.example.gobishops.view.MapActivity
import com.joooonho.SelectableRoundedImageView


/**
 * Created by Yee on 2021/3/29.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class DishAdapter(var dishes: ArrayList<Item>, context: Context?): RecyclerView.Adapter<DishAdapter.DishHolder>() {
    val mContext: Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_dishes, parent, false)
        return DishHolder(itemView)
    }

    override fun onBindViewHolder(holder: DishHolder, position: Int) {
        val dish = dishes[position]
        holder.title.text = dish.name
        holder.restaurant.text = dish.restaurantId.toString()
        holder.price.text = TextUtil.getItemPrice(dish.price)

        holder.background.setOnClickListener {
            it.context.startActivity(Intent(it.context, DishActivity::class.java))
        }

        for (i in 1..3){
            holder.rateLayout.addView(addStar(3))
        }
        holder.rateLayout.addView(addStar(2))
        holder.rateLayout.addView(addStar(1))

    }

    /**
     * Add stars to LinearLayout dynamically
     */
    private fun addStar(type: Int): ImageView{
        val ivStar = ImageView(mContext)
        val size = 60
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(size, size)
        params.setMargins(0, 0, 20, 0)
        ivStar.background = mContext!!.getDrawable(R.drawable.ic_star1)
        if (type == 1){
            ivStar.layoutParams = params
            ivStar.background = mContext!!.getDrawable(R.drawable.ic_star1)
        }
        if (type == 2){
            ivStar.layoutParams = params
            ivStar.background = mContext!!.getDrawable(R.drawable.ic_star2)
        }
        if (type == 3){
            ivStar.layoutParams = params
            ivStar.background = mContext!!.getDrawable(R.drawable.ic_star3)
        }
        return ivStar
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    class DishHolder(view: View): RecyclerView.ViewHolder(view){
        var title: TextView = view.findViewById(R.id.tv_dish_title)
        var restaurant: TextView = view.findViewById(R.id.tv_dish_restaurant)
        val price: TextView = view.findViewById(R.id.tv_dish_time)
        val background: mImageView = view.findViewById(R.id.iv_dish_image)
        val rateLayout: LinearLayout = view.findViewById(R.id.ll_dish_rate)
        init {

        }
    }
}