package com.example.gobishops.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.contract.BaseContract
import com.example.gobishops.contract.LoginContract
import com.example.gobishops.presenter.LoginPresenter
import com.example.gobishops.utils.*
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login_v2.*
import kotlinx.android.synthetic.main.activity_register_v2.*

class LoginActivity : AppCompatActivity(), LoginContract.View, BaseContract.OnDataRetrieved{
    private var presenter: LoginContract.Presenter? = null
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_v2)
        mContext = this
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
//            AuthUtil.signInAccount(et_login_name_v2.text.toString(), et_login_pwd_v2.text.toString(), this, this)

            Thread {
                val result = HttpJavaUtil.LoginByPost(et_login_name_v2.text.toString(), et_login_pwd_v2.text.toString())
                val bundle = Bundle()
                bundle.putString(ConstantUtil.SERVER_RESULT, result)
                val message = Message()
                message.data = bundle
                message.what = ConstantUtil.HANDLER_LOGIN
                handler.sendMessage(message)
            }.start()
        }
        tv_login_forget_v2.setOnClickListener {
            startActivity(Intent(this, ForgetPwdActivity::class.java))
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

    //TODO memory leak here
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                ConstantUtil.HANDLER_LOGIN -> {
                    var bundle = msg.data
                    val result = bundle.getString(ConstantUtil.SERVER_RESULT)
                    //Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
                    try {
                        if (!result.isNullOrEmpty()) {
                            LoginStateUtil.setIsLogin(true)
                            val user = EntityUtil.jsonToUser(result)
                            LoginStateUtil.setUser(user)
                            mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        } else {
                            mContext.startActivity(Intent(mContext, ResultActivity::class.java))
                        }
                        finish()
                    } catch (e: java.lang.NullPointerException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}