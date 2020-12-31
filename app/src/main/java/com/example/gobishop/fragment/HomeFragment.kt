package com.example.gobishop.fragment

import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gobishop.R
import com.example.gobishop.utils.TextUtil
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

    private fun initView(){
        var ivPortrait:SelectableRoundedImageView = view!!.findViewById(R.id.iv_home_portrait)
        Glide.with(context!!)
            .asBitmap()
            .load(R.drawable.img_portrait)
            .into(ivPortrait)
        tv_home_1.text = initText(getString(R.string.f_home_activity))
        tv_home_2.text = initText(getString(R.string.f_home_activity))
        tv_home_3.text = initText(getString(R.string.f_home_activity))

    }

    private fun initText(text: String): SpannableString{
        val spannable = SpannableString(text)
        var index: ArrayList<Int> = TextUtil.getCharIndex(text, "\n")
        spannable.setSpan(StyleSpan(BOLD), index[0], index[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD), index[1], index[2], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD), index[3], index[4], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(BOLD), index[5], index[6], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
}