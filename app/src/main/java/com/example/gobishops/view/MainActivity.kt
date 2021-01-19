package com.example.gobishops.view

/**
 * Created by Yee on 2020/12/24.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gobishops.R
import com.example.gobishops.contract.MainContract
import com.example.gobishops.fragment.*
import com.example.gobishops.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.annotations.TestOnly


class MainActivity : AppCompatActivity(), MainContract.View{
    private var presenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        test()
    }

    /**
     * Only for test
     */
    @TestOnly fun test(){
//        DBUtil.addEntity(ConstantUtil.DATABASE_USER+"/User002", User("Grindewald", getString(R.string.f_email)))
//        AuthUtil.createAccount(getString(R.string.f_email), getString(R.string.f_password))
    }


    /**
     * View Initialization, including onClickListener()
     */
    override fun initMainView(isInitial: Boolean){
        if (isInitial){
            val fragment = HomeFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container_main,
                fragment,
                fragment.javaClass.simpleName
            ).commit()
            bottom_nav_main.selectedItemId = R.id.nav_home
        }
        bottom_nav_main.setOnNavigationItemSelectedListener {
            val selectedFragment: Fragment
            when (it.itemId){
                R.id.nav_activity -> {
                    Log.i(getString(R.string.tag_main), "Activity Clicked")
                    selectedFragment = ActivityFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_main,
                        selectedFragment,
                        selectedFragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_market -> {
                    Log.i(getString(R.string.tag_main), "Market Clicked")
                    selectedFragment = MarketFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_main,
                        selectedFragment,
                        selectedFragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_home -> {
                    Log.i(getString(R.string.tag_main), "Home Clicked")
                    selectedFragment = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_main,
                        selectedFragment,
                        selectedFragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_appointment -> {
                    Log.i(getString(R.string.tag_main), "Appoint Clicked")
                    selectedFragment = ReservationFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_main,
                        selectedFragment,
                        selectedFragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_group -> {
                    Log.i(getString(R.string.tag_main), "Group Clicked")
                    selectedFragment = GroupFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_main,
                        selectedFragment,
                        selectedFragment.javaClass.simpleName
                    ).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun initView() {
        // Just override the Interface
    }
    override fun updateViewData() {
        TODO("Not yet implemented")
    }


}