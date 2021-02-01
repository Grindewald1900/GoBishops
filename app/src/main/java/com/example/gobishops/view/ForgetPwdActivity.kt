package com.example.gobishops.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gobishops.R
import com.example.gobishops.contract.ForgetPwdContract
import com.example.gobishops.presenter.ForgetPwdPresenter
import kotlinx.android.synthetic.main.activity_forget_pwd.*

class ForgetPwdActivity : AppCompatActivity(), ForgetPwdContract.View{
    private var presenter: ForgetPwdContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        presenter = ForgetPwdPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView() {
        btn_activity_forget_pwd_back.setOnClickListener {
            onBackPressed()
        }
        iv_activity_forget_pwd_email.setOnClickListener {
            val intent = Intent(this, VerifyActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        TODO("Not yet implemented")
    }
}