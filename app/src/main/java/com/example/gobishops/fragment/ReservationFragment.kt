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
import com.example.gobishops.adapter.EventAdapter
import com.example.gobishops.contract.BaseContract
import com.example.gobishops.entity.Event
import com.example.gobishops.utils.TypeUtil
import com.example.gobishops.view.AddEventActivity
import com.example.gobishops.view.EventSearchActivity
import kotlinx.android.synthetic.main.fragment_appointment.*
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_event.rv_activity_event

/**
 * Created by Yee on 2020/12/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ReservationFragment : Fragment(), BaseContract.OnDataRetrieved {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var orderAdapter: EventAdapter
    companion object{
        fun newInstance(): ReservationFragment{
            return ReservationFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_appointment, container, false)
    }

    /**
     * Initialize view after the fragment view has been created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     *  Call back when data received from firebase
     */
    override fun getRetrievedData(state: Int, data: Any?) {
        var events: ArrayList<Event> = TypeUtil.hashMapToEvent(data as HashMap<*, *>)
//        refreshView(events)
    }

    /**
     * View Initialization, including onClickListener()
     */
    private fun initView(){
        // Initialize recycle view of activities
        val data: ArrayList<Event> = ArrayList()
        val fakeEvent = Event()

        for (i in 0..10){
            data.add(fakeEvent)
        }
        linearLayoutManager = LinearLayoutManager(context)
        orderAdapter = EventAdapter(data)
        rv_activity_order.layoutManager = linearLayoutManager
        rv_activity_order.itemAnimator = DefaultItemAnimator()
        rv_activity_order.adapter = orderAdapter
        rv_activity_order.setOnClickListener {
        }

        swipe_activity_order.setOnRefreshListener {
//            DBUtil.getEntity(
//                ConstantUtil.DATABASE_EVENT,
//                fakeEvent,
//                this)
        }
        view_activity_order_box.setOnClickListener {
            val intent = Intent(context, EventSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshView(data: ArrayList<Event>){
        orderAdapter = EventAdapter(data)
        rv_activity_event.adapter = orderAdapter
        swipe_activity_event.isRefreshing = false
    }
}