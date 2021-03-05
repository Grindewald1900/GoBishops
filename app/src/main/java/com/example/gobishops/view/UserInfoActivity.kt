package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.adapter.ScheduleCardAdapter
import com.example.gobishops.contract.UserInfoContract
import com.example.gobishops.entity.Event
import com.example.gobishops.presenter.UserInfoPresenter
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity(), UserInfoContract.View{
    private var presenter: UserInfoContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        presenter = UserInfoPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView() {
        val data: ArrayList<Event> = ArrayList()
        Glide.with(this).asBitmap().load(R.drawable.img_portrait).into(btn_activity_user_info_portrait)

        for(i in 1..10){
            data.add(Event())
        }
        rv_activity_user_info_home.itemAnimator = DefaultItemAnimator()
        rv_activity_user_info_home.layoutManager = LinearLayoutManager(this)
        rv_activity_user_info_home.adapter = ScheduleCardAdapter(data)
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}