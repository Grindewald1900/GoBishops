package com.example.gobishops.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gobishops.R
import com.example.gobishops.contract.VerifyContract
import com.example.gobishops.presenter.VerifyPresenter
import com.example.gobishops.utils.AuthUtil
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import kotlinx.android.synthetic.main.activity_verify.*

class VerifyActivity : AppCompatActivity(), VerifyContract.View {
    private var presenter: VerifyContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)
        presenter = VerifyPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView() {
        btn_activity_verify_back.setOnClickListener {
            onBackPressed()
        }
        btn_activity_verify_email.setOnClickListener {
            val email = et_activity_verify_email.text.toString()
            AuthUtil.sendSignInEmail(email)
        }
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}