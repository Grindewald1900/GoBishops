package com.example.gobishop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gobishop.R
import kotlinx.android.synthetic.main.fragment_activity.*

/**
 * Created by Yee on 2020/12/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class ActivityFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    companion object{
        fun newInstance(): ActivityFragment{
            return ActivityFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    private fun initView(){
        linearLayoutManager = LinearLayoutManager(context)
        rv_activity_main.layoutManager = linearLayoutManager
    }
}