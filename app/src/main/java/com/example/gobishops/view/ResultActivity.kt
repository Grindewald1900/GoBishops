package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gobishops.R
import com.example.gobishops.utils.ConstantUtil
import kotlinx.android.synthetic.main.activity_result.*
import java.security.AccessController.getContext
import kotlin.properties.Delegates

class ResultActivity : AppCompatActivity() {
    var state = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        state = intent.getIntExtra(ConstantUtil.STRING_RESULT_ACTIVITY, ConstantUtil.RESULT_DEFAULT)
        initView()

    }

    private fun initView(){
        when(state){
            ConstantUtil.RESULT_CORRECT -> {
                iv_activity_success_img.background = getDrawable(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                tv_activity_result_text.text = getText(R.string.all_done)
            }

            ConstantUtil.RESULT_INCORRECT ->{
                iv_activity_success_img.background = getDrawable(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
                tv_activity_result_text.text = getText(R.string.try_again)
            }

            else -> {
                iv_activity_success_img.background = getDrawable(R.drawable.ic_baseline_sentiment_neutral_24)
                tv_activity_result_text.text = getText(R.string.request_upload)
            }
        }
        btn_activity_result_back.setOnClickListener {
            onBackPressed()
            finish()
        }

    }
}