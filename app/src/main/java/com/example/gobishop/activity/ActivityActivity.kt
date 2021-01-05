package com.example.gobishop.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.gobishop.R
import kotlinx.android.synthetic.main.activity_activity.*

class ActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)
        initView()
    }

    private fun initView(){
        Glide.with(this).asBitmap().load(R.drawable.img_portrait).into(iv_activity_detail_portrait)
    }
}