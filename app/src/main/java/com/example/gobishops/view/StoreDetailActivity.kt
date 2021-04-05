package com.example.gobishops.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.adapter.DishAdapter
import com.example.gobishops.adapter.NormalCardAdapter
import com.example.gobishops.entity.Item
import com.example.gobishops.utils.*
import kotlinx.android.synthetic.main.activity_dish.*
import kotlinx.android.synthetic.main.activity_store_detail.*
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_home.*

class StoreDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        initView(intent.getStringExtra("Location"), intent.getStringExtra("Price"), intent.getIntExtra("id", 0))
    }

    private fun initView(location: String?, price: String?, id: Int){
        tv_activity_store_detail_location.text = location
        tv_activity_store_detail_price.text = price

        if(LoginStateUtil.getIsLogin()){
            tv_activity_store_detail_initiator.text = LoginStateUtil.getUser()!!.userName
            Glide.with(this).asBitmap().load(R.drawable.img_portrait).into(iv_activity_dish_portrait)
        }else{
            Glide.with(this).asBitmap().load(R.drawable.ic_male_user_30).into(iv_activity_store_detail_portrait)
        }
        btn_activity_store_detail_back.setOnClickListener {
            onBackPressed()
            finish()
        }

        iv_activity_store_detail_portrait.setOnClickListener {
            if (LoginStateUtil.getIsLogin()){
                startActivity(Intent(this, UserInfoActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        Thread{
            val dishes = HttpJavaUtil.GetDishByPost(id)
            val bundle = Bundle()
            val message = Message()
            bundle.putString(ConstantUtil.SERVER_RESULT, dishes)
            message.data = bundle
            message.what = ConstantUtil.HANDLER_DISH
            handler.sendMessage(message)
        }.start()
    }

    private fun initRecyclerView(dishes: ArrayList<Item>){
        rv_activity_store_detail.layoutManager = LinearLayoutManager(this)
        rv_activity_store_detail.itemAnimator = DefaultItemAnimator()
        rv_activity_store_detail.adapter = DishAdapter(dishes, this)
    }

    //TODO memory leak here
    private var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                ConstantUtil.HANDLER_DISH -> {
                    var bundle = msg.data
                    var dishes: ArrayList<Item> = ArrayList()
                    val result = bundle.getString(ConstantUtil.SERVER_RESULT).toString()
                    try {
                        Log.e("JSONTAG", result)
                        if (!TextUtil.isEmpty(result)) {
                            dishes = EntityUtil.jsonToItemList(result)
                            initRecyclerView(dishes)
                        } else {
                            // Login unsuccessfully

                        }
                    } catch (e: java.lang.NullPointerException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}