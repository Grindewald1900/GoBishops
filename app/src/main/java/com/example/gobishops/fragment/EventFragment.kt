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
import com.example.gobishops.adapter.DishAdapter
import com.example.gobishops.view.AddEventActivity
import com.example.gobishops.adapter.EventAdapter
import com.example.gobishops.contract.BaseContract
import com.example.gobishops.entity.Event
import com.example.gobishops.entity.Item
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.DBUtil
import com.example.gobishops.utils.TypeUtil
import com.example.gobishops.view.EventSearchActivity
import kotlinx.android.synthetic.main.fragment_event.*

/**
 * Created by Yee on 2020/12/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class EventFragment : Fragment(), BaseContract.OnDataRetrieved{
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dishAdapter: DishAdapter
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
        val data: ArrayList<Item> = ArrayList()
        var fakeEvent: Item
        for (i in 0..10){
            fakeEvent = Item(i)
            data.add(fakeEvent)
        }
        linearLayoutManager = LinearLayoutManager(context)
        dishAdapter = DishAdapter(data, context)
        rv_activity_event.layoutManager = linearLayoutManager
        rv_activity_event.itemAnimator = DefaultItemAnimator()
        rv_activity_event.adapter = dishAdapter
        rv_activity_event.setOnClickListener {
        }

        btn_activity_event_add.setOnClickListener {
            val intent = Intent(context, AddEventActivity::class.java)
            startActivity(intent)
        }
        swipe_activity_event.setOnRefreshListener {
//            DBUtil.getEntity(
//                ConstantUtil.DATABASE_EVENT,
//                fakeEvent,
//                this)
        }
        view_activity_event_search_box.setOnClickListener {
            val intent = Intent(context, EventSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshView(dishes: ArrayList<Item>){
        dishAdapter = DishAdapter(dishes, context)
        rv_activity_event.adapter = dishAdapter
        swipe_activity_event.isRefreshing = false
    }
}