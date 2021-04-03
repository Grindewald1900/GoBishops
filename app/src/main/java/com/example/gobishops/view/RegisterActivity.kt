package com.example.gobishops.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gobishops.R
import com.example.gobishops.contract.RegisterContract
import com.example.gobishops.entity.User
import com.example.gobishops.presenter.RegisterPresenter
import com.example.gobishops.utils.App
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.HttpJavaUtil
import com.example.gobishops.utils.LoginStateUtil
import kotlinx.android.synthetic.main.activity_dish.*
import kotlinx.android.synthetic.main.activity_register_v2.*
import java.util.*


class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    private var presenter: RegisterContract.Presenter? = null
    private lateinit var mUser: User
    private lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_v2)
        mContext = this
        presenter = RegisterPresenter(this)
    }
    init {

    }

    override fun initView() {
        btn_register_register_v2.setOnClickListener {
            if (et_register_name_v2.text.isNullOrEmpty()){
                showToast(getString(R.string.hint_no_name))
                return@setOnClickListener
            }
            if (et_register_pwd1_v2.text.isNullOrEmpty()){
                showToast(getString(R.string.hint_no_password))
                return@setOnClickListener
            }
            if (et_register_pwd2_v2.text.isNullOrEmpty()){
                showToast(getString(R.string.hint_no_password))
                return@setOnClickListener
            }
            if (et_register_email_v2.text.isNullOrEmpty()){
                showToast(getString(R.string.hint_no_email))
                return@setOnClickListener
            }
            if (et_register_pwd1_v2.text.toString().compareTo(et_register_pwd2_v2.text.toString()) != 0){
                val a = et_register_pwd1_v2.text
                val b = et_register_pwd2_v2.text
                showToast(getString(R.string.hint_pwd_different))
                return@setOnClickListener
            }
            if (!et_register_email_v2.text.contains('@')){
                showToast(getString(R.string.hint_invalid_email))
                return@setOnClickListener
            }
            if (et_register_email_v2.text.contains("ubishops")){
                showToast(getString(R.string.hint_welcome_bu))
            }

            val fakeUserId = (0..10000).random()
            mUser = User(fakeUserId, et_register_name_v2.text.toString(), et_register_email_v2.text.toString(), Date(1994, 9, 24), 'M', "8008008000", "")
            Thread {
                val result: String = HttpJavaUtil.RegisterByPost(
                    fakeUserId.toString(),
                    et_register_name_v2.text.toString(),
                    et_register_pwd1_v2.text.toString(),
                    et_register_email_v2.text.toString()
                )
//                HttpUtil.registerByPost((0..10000).random(), et_register_name_v2.text.toString(),et_register_pwd1_v2.text.toString(), et_register_email_v2.text.toString(), ByteArrayOutputStream())

                val bundle = Bundle()
                bundle.putString("result", result)
                val message = Message()
                message.data = bundle
                message.what = ConstantUtil.HANDLER_LOGIN
                handler.sendMessage(message)
            }.start()

//            btn_register_register_v2.startAnimation();
//            handler.postDelayed(Runnable {
//                HttpUtil.RegisterByPost((0..10000).random(), et_register_name_v2.text.toString(),et_register_pwd1_v2.text.toString(), et_register_email_v2.text.toString(), ByteArrayOutputStream())
//                var isSuccessful: Boolean = true
//                if(isSuccessful){
//                    btn_register_register_v2.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, TransitionButton.OnAnimationStopEndListener {
//                        //Start Activity here
//                        startActivity(Intent(this, MainActivity::class.java))
//                        showToast(getText(R.string.register_success).toString())
//                    })
//                }
//            }, 1000)
        }
    }
    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

//    private class MyHandler(activity: Activity): Handler(){
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            when (msg.what) {
//                ConstantUtil.HANDLER_LOGIN -> {
//                    val bundle = msg.data
//                    val result = bundle.getString(ConstantUtil.SERVER_RESULT)
//                    Toast.makeText(App.mContext, result, Toast.LENGTH_SHORT).show();
//                    try {
//                        if (result == ConstantUtil.SERVER_SUCCESS) {
//                            Toast.makeText(App.mContext, "SUCCESS", Toast.LENGTH_SHORT).show();
//
////                                val s: String = et_register_name_v2.getText().toString()
////                                setResult(1, Intent().putExtra("name", etName.getText().toString()))
//                        }
//                    } catch (e: NullPointerException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//        }
//    }

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
                        if (result == ConstantUtil.SERVER_SUCCESS) {
                            LoginStateUtil.setIsLogin(true)
                            LoginStateUtil.setUser(mUser)
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