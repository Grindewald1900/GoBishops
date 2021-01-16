package com.example.gobishops.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.gobishops.R
import kotlinx.android.synthetic.main.activity_detail_activity.*

class ActivityDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)
        initView()
    }

    private fun initView(){
        Glide.with(this).asBitmap().load(R.drawable.img_portrait).into(iv_activity_detail_portrait)
        btn_activity_detail_back.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}