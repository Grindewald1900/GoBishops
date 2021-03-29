package com.example.gobishops.fragment

import android.content.Intent
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.adapter.NormalCardAdapter
import com.example.gobishops.entity.NormalCard
import com.example.gobishops.utils.AuthUtil
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.view.LoginActivity
import com.example.gobishops.utils.TextUtil
import com.example.gobishops.view.RegisterActivity
import com.example.gobishops.view.UserInfoActivity
import com.joooonho.SelectableRoundedImageView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Yee on 2020/12/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class HomeFragment : Fragment() {
    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        // can not be invoked in onCreateView, since the view has not be initialized.
        initView()
    }


    /**
     * View Initialization, including onClickListener()
     */
    private fun initView(){
        val data: ArrayList<NormalCard> = ArrayList()
        val spinnerFilter: Spinner = view!!.findViewById(R.id.spinner_fragment_home_type)

        ArrayAdapter.createFromResource(context!!, R.array.home_search_type, R.layout.spinner_item_simple).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFilter.adapter = it
        }

        // Prepare data for event list
        for (i in 1..10){
            data.add(NormalCard())
        }
        rv_fragment_home.layoutManager = LinearLayoutManager(context)
        rv_fragment_home.itemAnimator = DefaultItemAnimator()
        rv_fragment_home.adapter = NormalCardAdapter(data)
        iv_fragment_home_profile.setOnClickListener {
            if (AuthUtil.isSignedIn()){
                startActivity(Intent(context, UserInfoActivity::class.java))
            }else{
                startActivity(Intent(context, LoginActivity::class.java))
            }
        }
//        Glide.with(context!!)
//            .asBitmap()
//            .load(R.drawable.img_portrait)
//            .into(ivPortrait)
//        tv_home_1.text = initText(getString(R.string.f_home_activity))
//        tv_home_2.text = initText(getString(R.string.f_home_activity))
//        tv_home_3.text = initText(getString(R.string.f_home_activity))
//        iv_home_portrait.setOnClickListener {
//            if(null == AuthUtil.getCurrentUser()){
//                startActivity(Intent(context, LoginActivity::class.java))
//            }
//        }

    }

    /**
     * Deprecated
     * @param text: string to be processed
     */
    @Deprecated("This method is deprecated, since home page no longer need this style")
    private fun initText(text: String): SpannableString{
        val spannable = SpannableString(text)
        val index: ArrayList<Int> = TextUtil.getCharIndex(text, "\n")
        spannable.setSpan(StyleSpan(BOLD), index[0], index[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD), index[1], index[2], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD), index[3], index[4], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD), index[5], index[6], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
}