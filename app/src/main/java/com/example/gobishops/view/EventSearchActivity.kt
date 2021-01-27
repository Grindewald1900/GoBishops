package com.example.gobishops.view

import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import com.example.gobishops.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.gobishops.adapter.EventResultAdapter
import com.example.gobishops.entity.Event
import com.example.gobishops.fragment.ViewpagerSearchFragment
import com.example.gobishops.transformers.ZoomOutPageTransformer
import com.example.gobishops.utils.ConstantUtil
import kotlinx.android.synthetic.main.activity_event_search.*

class EventSearchActivity : FragmentActivity() {
    private lateinit var mPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_search)
        initView()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun initView(){
        val pagerAdapter: ScreenSlidePagerAdapter = ScreenSlidePagerAdapter(this)
        val data: ArrayList<Event> = ArrayList()
        mPager = findViewById(R.id.pager_activity_event_search)
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(MarginPageTransformer(20))
        mPager.setPageTransformer(ZoomOutPageTransformer())
        mPager.offscreenPageLimit = 5
        for (i in 0..10){
            data.add(Event())
        }
        rv_activity_event_search.adapter = EventResultAdapter(data)
        rv_activity_event_search.layoutManager = LinearLayoutManager(this)
        rv_activity_event_search.itemAnimator = DefaultItemAnimator()

        btn_activity_event_search_back.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = Integer.MAX_VALUE
        override fun getItemId(position: Int): Long {
            return super.getItemId(position)
        }
        override fun createFragment(position: Int): Fragment = getFragmentByPosition(position)
        private fun getFragmentByPosition(position: Int): Fragment{
            var pos = position % ConstantUtil.MAX_SEARCH_HISTORY
            return ViewpagerSearchFragment()

//            when(pos){
//                0 -> return ViewpagerSearchFragment()
//                else -> {
//                    return ViewpagerSearchFragment()
//                }
//            }
        }
    }
}