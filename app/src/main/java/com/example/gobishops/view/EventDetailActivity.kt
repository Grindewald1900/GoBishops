package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.contract.EventDetailContract
import com.example.gobishops.presenter.EventDetailPresenrer
import kotlinx.android.synthetic.main.activity_detail_event.*

class EventDetailActivity : AppCompatActivity(), EventDetailContract.View {
    private var presenter: EventDetailContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)
        presenter = EventDetailPresenrer(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView(){
        Glide.with(this).asBitmap().load(R.drawable.img_portrait1).into(iv_activity_detail_portrait)
        btn_activity_detail_back.setOnClickListener {
            onBackPressed()
            finish()
        }
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}