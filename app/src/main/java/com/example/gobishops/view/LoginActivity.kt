package com.example.gobishops.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.contract.LoginContract
import com.example.gobishops.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login_v2.*

class LoginActivity : AppCompatActivity(), LoginContract.View{
    private var presenter: LoginContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_v2)
        presenter = LoginPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView(){
        tv_login_register_v2.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        Glide.with(this)
            .asBitmap()
            .load(R.drawable.img_portrait)
            .into(iv_login_portrait_v2)
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}