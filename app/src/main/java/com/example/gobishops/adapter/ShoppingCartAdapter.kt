package com.example.gobishops.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.snappingstepper.SnappingStepper
import com.example.gobishops.R
import com.example.gobishops.entity.Item
import com.example.gobishops.entity.OrderItem
import com.example.gobishops.fragment.MarketFragment
import com.example.gobishops.utils.SharedPreferencesUtil
import com.example.gobishops.utils.TextUtil
import kotlin.math.roundToInt


/**
 * Created by Yee on 2021/3/28.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ShoppingCartAdapter(var orders: ArrayList<OrderItem>, var mFragment: MarketFragment): RecyclerView.Adapter<ShoppingCartAdapter.CartHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_shopping_cart, parent, false)
        return CartHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        var totalPrice: Float
        val order = orders[position]
        totalPrice = order.count * order.item!!.price
        holder.title.text = order.item.name
        holder.description.text = order.item.id.toString()
        holder.price.text = TextUtil.getItemPrice(totalPrice)
        holder.stepper.setOnValueChangeListener { view, value ->
            totalPrice = value * order.item.price
            // Keep 2 decimals for the price, e.g. 2.50$
            holder.price.text = TextUtil.getItemPrice(totalPrice)
            SharedPreferencesUtil.saveOrder(OrderItem(order.item, value))
            if (value == 0){
//                holder.mView.visibility = View.GONE
                SharedPreferencesUtil.removeOrder(order)
                mFragment.updateAdapter(position)
            }else{
                mFragment.updateAdapter(-1)
            }
        }
        holder.stepper.value = order.count
        mFragment.updateAdapter(-1)
    }

    override fun getItemCount(): Int {
        return orders.size
    }


    class CartHolder(view: View): RecyclerView.ViewHolder(view){
        var mView = view
        var image: ImageView = view.findViewById(R.id.iv_shopping_cart_icon)
        var title: TextView = view.findViewById(R.id.tv_shopping_cart_title)
        var description: TextView = view.findViewById(R.id.tv_shopping_cart_description)
        var price: TextView = view.findViewById(R.id.tv_shopping_cart_price)
        var stepper: SnappingStepper = view.findViewById(R.id.stepper_shopping_cart)

        init {

        }


    }
}