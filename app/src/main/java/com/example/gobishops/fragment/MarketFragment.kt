package com.example.gobishops.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gobishops.R
import com.example.gobishops.adapter.ShoppingCartAdapter
import com.example.gobishops.entity.Item
import com.example.gobishops.entity.NormalCard
import com.royrodriguez.transitionbutton.TransitionButton
import kotlinx.android.synthetic.main.fragment_market.*

/**
 * Created by Yee on 2020/12/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class MarketFragment : Fragment() {
    companion object{
        fun newInstance(): MarketFragment{
            return MarketFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onStart() {
        super.onStart()
        initView()
    }

    /**
     * View Initialization, including onClickListener()
     */
    private fun initView(){
        val data: ArrayList<Item> = ArrayList()
        val handler: Handler = Handler()

        // Prepare data for event list
        for (i in 1..10){
            data.add(Item())
        }
        rv_activity_cart.layoutManager = LinearLayoutManager(context)
        rv_activity_cart.itemAnimator = DefaultItemAnimator()
        rv_activity_cart.adapter = ShoppingCartAdapter(data)

        btn_activity_cart_checkout.setOnClickListener {
            btn_activity_cart_checkout.startAnimation();
            handler.postDelayed(Runnable {
                var isSuccessful: Boolean = true
                if(isSuccessful){
                    btn_activity_cart_checkout.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, TransitionButton.OnAnimationStopEndListener {
                        //Start Activity here
                        Toast.makeText(context, getText(R.string.checkout_successful), Toast.LENGTH_SHORT).show()
                    })
                }
            }, 2000)
        }
    }
}