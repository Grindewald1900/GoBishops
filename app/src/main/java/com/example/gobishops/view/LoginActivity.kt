package com.example.gobishops.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gobishops.R
import com.example.gobishops.contract.LoginContract
import com.example.gobishops.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View{
    private var presenter: LoginContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView(){
        tv_login_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}