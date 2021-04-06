package com.example.gobishops.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.contract.DishContract
import com.example.gobishops.entity.Item
import com.example.gobishops.entity.OrderItem
import com.example.gobishops.entity.Store
import com.example.gobishops.presenter.DishPresenter
import com.example.gobishops.utils.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.royrodriguez.transitionbutton.TransitionButton
import kotlinx.android.synthetic.main.activity_dish.*
import kotlinx.android.synthetic.main.fragment_market.*

class DishActivity : AppCompatActivity(),  OnMapReadyCallback, DishContract.View{
    private var presenter: DishContract.Presenter? = null
    private var dish: Item? = null
    private lateinit var mContext: Context
    private var mGoogleMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)
        dish = intent.getSerializableExtra(ConstantUtil.CLASS_ITEM) as? Item
        presenter = DishPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView(){
        val handler: Handler = Handler()

        if(LoginStateUtil.getIsLogin()){
            tv_activity_dish_initiator.text = LoginStateUtil.getUser()!!.userName
            Glide.with(this).asBitmap().load(R.drawable.img_portrait).into(iv_activity_dish_portrait)
        }else{
            Glide.with(this).asBitmap().load(R.drawable.ic_male_user_30).into(iv_activity_dish_portrait)
        }

        if(null != dish){
            tv_activity_dish_location.text = dish!!.name
            tv_activity_dish_price.text = TextUtil.getItemPrice(dish!!.price)
        }
        btn_activity_dish_back.setOnClickListener {
            onBackPressed()
            finish()
        }

        iv_activity_dish_portrait.setOnClickListener {
            if (LoginStateUtil.getIsLogin()){
                startActivity(Intent(this, UserInfoActivity::class.java))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        btn_activity_dish_add.setOnClickListener {
            btn_activity_dish_add.startAnimation();
            // One piece by default
            SharedPreferencesUtil.saveOrder(OrderItem(dish, 1))
            handler.postDelayed(Runnable {
                var isSuccessful: Boolean = true
                if(isSuccessful){
                    btn_activity_dish_add.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, TransitionButton.OnAnimationStopEndListener {
                        //Start Activity here
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(ConstantUtil.STRING_RESULT_ACTIVITY, ConstantUtil.RESULT_CORRECT)
                        startActivity(intent)
                        showToast(getText(R.string.checkout_successful).toString())
                    })
                }
            }, 1000)
        }

        // Initialize Google Map
        if (getString(R.string.maps_api_key).isEmpty()){
            showToast("Add your own API key in MapWithMarker/app/secure.properties as MAPS_API_KEY=YOUR_API_KEY")
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_activity_dish_map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    /**
     * Set maps when initialized
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        Thread{
            val result = HttpJavaUtil.GetStoreByPost(dish!!.restaurantId, 2)
            val bundle = Bundle()
            val message = Message()
            bundle.putString(ConstantUtil.SERVER_RESULT, result)
            message.data = bundle
            message.what = ConstantUtil.HANDLER_STORE
            handler.sendMessage(message)

        }.start()

    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun showMark(store: Store){
        mGoogleMap?.apply {
            val sydney = LatLng(store.lat.toDouble(), store.long.toDouble())
            addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title(store.name)
                    .snippet("${store.genre}: ${store.averagePrice}$")
            )
            mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            mGoogleMap!!.setMinZoomPreference(7f)
            mGoogleMap!!.setMaxZoomPreference(20f)
        }
    }

    //TODO memory leak here
    private var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                ConstantUtil.HANDLER_STORE -> {
                    var bundle = msg.data
                    var store: Store
                    val result = bundle.getString(ConstantUtil.SERVER_RESULT).toString()
                    try {
                        if (!TextUtil.isEmpty(result)) {
                            store = EntityUtil.jsonToStoreList(result)[0]
                            showMark(store)
                        } else {
                            // Login unsuccessfully
                        }
                    } catch (e: java.lang.NullPointerException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}