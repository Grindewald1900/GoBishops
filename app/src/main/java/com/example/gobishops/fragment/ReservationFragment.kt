package com.example.gobishops.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gobishops.R
import com.example.gobishops.adapter.EventAdapter
import com.example.gobishops.adapter.NormalCardAdapter
import com.example.gobishops.contract.BaseContract
import com.example.gobishops.entity.Event
import com.example.gobishops.entity.Order
import com.example.gobishops.entity.OrderItem
import com.example.gobishops.entity.Store
import com.example.gobishops.utils.*
import com.example.gobishops.view.AddEventActivity
import com.example.gobishops.view.EventSearchActivity
import kotlinx.android.synthetic.main.fragment_appointment.*
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_event.rv_activity_event
import kotlinx.android.synthetic.main.fragment_home.*

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
        if(!LoginStateUtil.getIsLogin()){
            return
        }
        // Prepare data for event list
        Thread{
            val result = HttpJavaUtil.OrderByPost(LoginStateUtil.getUser()!!.id, 1,1,1,1,1,2)
            val bundle = Bundle()
            val message = Message()
            bundle.putString(ConstantUtil.SERVER_RESULT, result)
            message.data = bundle
            message.what = ConstantUtil.HANDLER_STORE
            handler.sendMessage(message)
        }.start()


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


    private fun initRecyclerView(orders: ArrayList<Order>){
        rv_activity_order.layoutManager = LinearLayoutManager(context)
        rv_activity_order.itemAnimator = DefaultItemAnimator()
        rv_activity_order.adapter = EventAdapter(orders)
    }

    //TODO memory leak here
    private var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                ConstantUtil.HANDLER_STORE -> {
                    var bundle = msg.data
                    var stores: ArrayList<Order>
                    val result = bundle.getString(ConstantUtil.SERVER_RESULT).toString()
                    try {
                        if (!TextUtil.isEmpty(result)) {
                            stores = EntityUtil.jsonToOrderList(result)
                            initRecyclerView(stores)
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