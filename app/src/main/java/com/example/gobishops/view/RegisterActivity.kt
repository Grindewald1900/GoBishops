package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gobishops.R
import com.example.gobishops.contract.RegisterContract
import com.example.gobishops.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    private var presenter: RegisterContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(this)
    }

    override fun initView() {
    }
    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}