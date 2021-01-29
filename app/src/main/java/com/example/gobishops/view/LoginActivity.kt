package com.example.gobishops.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.contract.BaseContract
import com.example.gobishops.contract.LoginContract
import com.example.gobishops.presenter.LoginPresenter
import com.example.gobishops.utils.AuthUtil
import com.example.gobishops.utils.ConstantUtil
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login_v2.*

class LoginActivity : AppCompatActivity(), LoginContract.View, BaseContract.OnDataRetrieved{
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
        btn_login_login_v2.setOnClickListener {
            if(et_login_name_v2.text.toString().isEmpty()){
                showToast(getString(R.string.hint_no_email))
                return@setOnClickListener
            }
            if(et_login_pwd_v2.text.toString().isEmpty()){
                showToast(getString(R.string.hint_no_password))
                return@setOnClickListener
            }
            AuthUtil.signInAccount(et_login_name_v2.text.toString(), et_login_pwd_v2.text.toString(), this, this)
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

    override fun getRetrievedData(state: Int, data: Any?) {
        when(state){
            ConstantUtil.STATE_SUCCESS -> {
                val user = data as FirebaseUser
                startActivity(Intent(this, MainActivity::class.java))
            }
            ConstantUtil.STATE_FAIL -> {
                showToast(getString(R.string.hint_login_fail))
            }
        }
    }
}