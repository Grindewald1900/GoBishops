package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.contract.DishContract
import com.example.gobishops.presenter.DishPresenter
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.royrodriguez.transitionbutton.TransitionButton
import kotlinx.android.synthetic.main.activity_dish.*
import kotlinx.android.synthetic.main.fragment_market.*

class DishActivity : AppCompatActivity(),  OnMapReadyCallback, DishContract.View{
    private var presenter: DishContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)
        presenter = DishPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView(){
        val handler: Handler = Handler()

        Glide.with(this).asBitmap().load(R.drawable.img_portrait).into(iv_activity_dish_portrait)
        btn_activity_dish_back.setOnClickListener {
            onBackPressed()
            finish()
        }
        if (getString(R.string.maps_api_key).isEmpty()){
            showToast("Add your own API key in MapWithMarker/app/secure.properties as MAPS_API_KEY=YOUR_API_KEY")
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_activity_dish_map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        btn_activity_dish_add.setOnClickListener {
            btn_activity_dish_add.startAnimation();
            handler.postDelayed(Runnable {
                var isSuccessful: Boolean = true
                if(isSuccessful){
                    btn_activity_dish_add.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, TransitionButton.OnAnimationStopEndListener {
                        //Start Activity here
                        showToast(getText(R.string.checkout_successful).toString())
                    })
                }
            }, 2000)
        }
    }

    /**
     * Set maps when initialized
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.apply {
            val sydney = LatLng(-33.852, 151.211)
            addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
        }
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}