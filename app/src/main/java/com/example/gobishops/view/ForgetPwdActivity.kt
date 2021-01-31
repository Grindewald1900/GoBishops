package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gobishops.R
import com.example.gobishops.contract.ForgetPwdContract
import kotlinx.android.synthetic.main.activity_forget_pwd.*

class ForgetPwdActivity : AppCompatActivity(), ForgetPwdContract.View{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView() {
        btn_activity_forget_pwd_back.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        TODO("Not yet implemented")
    }
}