package com.example.gobishop.activity

/**
 * Created by Yee on 2020/12/24.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.gobishop.R
import com.example.gobishop.fragment.AppointmentFragment
import com.example.gobishop.fragment.GroupFragment
import com.example.gobishop.fragment.HomeFragment
import com.example.gobishop.fragment.MarketFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navListener: BottomNavigationView.OnNavigationItemSelectedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(getString(R.string.tag_main), "Main Created")
        initView(savedInstanceState)

    }

    fun initView(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            val fragment = HomeFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, fragment, fragment.javaClass.simpleName).commit()
        }
        bottom_nav_main.setOnNavigationItemSelectedListener {
            val selectedFragment: Fragment
            when (it.itemId){
                R.id.nav_home ->{
                    Log.i(getString(R.string.tag_main), "Home Clicked")
                    selectedFragment = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment, selectedFragment.javaClass.simpleName).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_market ->{
                    Log.i(getString(R.string.tag_main), "Market Clicked")
                    selectedFragment = MarketFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment, selectedFragment.javaClass.simpleName).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_appointment ->{
                    Log.i(getString(R.string.tag_main), "Appoint Clicked")
                    selectedFragment = AppointmentFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment, selectedFragment.javaClass.simpleName).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_group -> {
                    Log.i(getString(R.string.tag_main), "Group Clicked")
                    selectedFragment = GroupFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment, selectedFragment.javaClass.simpleName).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}