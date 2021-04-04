package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gobishops.R
import com.example.gobishops.contract.OrderContract
import com.example.gobishops.entity.OrderItem
import com.example.gobishops.presenter.OrderPresenter

class OrderActivity : AppCompatActivity(), OrderContract.View {
    lateinit var mPresenter: OrderContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        mPresenter = OrderPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView() {
        val data: ArrayList<OrderItem>?

    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}