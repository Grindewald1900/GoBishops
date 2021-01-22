package com.example.gobishops.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gobishops.R
import com.example.gobishops.view.AddEventActivity
import com.example.gobishops.adapter.ActivityAdapter
import com.example.gobishops.entity.BuActivity
import kotlinx.android.synthetic.main.fragment_event.*

/**
 * Created by Yee on 2020/12/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class EventFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var activityAdapter: ActivityAdapter
    companion object{
        fun newInstance(): EventFragment{
            return EventFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        // Initialize recycle view of activities
        var data: ArrayList<BuActivity> = ArrayList()
        for (i in 0..10){
            val activity: BuActivity = BuActivity("Title test", "2021.09.01  @Oxford", "This is a description of our activity\nThis is a description of our activity", "Released at 7:05PM, Jan 1, 2021 by Grindewald1900 ")
            data.add(activity)
        }
        linearLayoutManager = LinearLayoutManager(context)
        activityAdapter = ActivityAdapter(data)
        rv_activity_main.layoutManager = linearLayoutManager
        rv_activity_main.itemAnimator = DefaultItemAnimator()
        rv_activity_main.adapter = activityAdapter
        rv_activity_main.setOnClickListener {
        }

        btn_activity_add.setOnClickListener {
            val intent = Intent(context, AddEventActivity::class.java)
            startActivity(intent)
        }

    }
}