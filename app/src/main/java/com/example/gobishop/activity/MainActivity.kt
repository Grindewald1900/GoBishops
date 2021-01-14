package com.example.gobishop.activity

/**
 * Created by Yee on 2020/12/24.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.gobishop.R
import com.example.gobishop.fragment.*
import com.example.gobishop.utils.DBUtil
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(getString(R.string.tag_main), "Main Created")
        initView(savedInstanceState)
//        test()
    }

    private fun test(){
        setValue()
    }

    private fun initView(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            val fragment = HomeFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, fragment, fragment.javaClass.simpleName).commit()
            bottom_nav_main.selectedItemId = R.id.nav_home
        }
        bottom_nav_main.setOnNavigationItemSelectedListener {
            val selectedFragment: Fragment
            when (it.itemId){
                R.id.nav_activity ->{
                    Log.i(getString(R.string.tag_main), "Activity Clicked")
                    selectedFragment = ActivityFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment, selectedFragment.javaClass.simpleName).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_market ->{
                    Log.i(getString(R.string.tag_main), "Market Clicked")
                    selectedFragment = MarketFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment, selectedFragment.javaClass.simpleName).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_home ->{
                    Log.i(getString(R.string.tag_main), "Home Clicked")
                    selectedFragment = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main, selectedFragment, selectedFragment.javaClass.simpleName).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_appointment ->{
                    Log.i(getString(R.string.tag_main), "Appoint Clicked")
                    selectedFragment = ReservationFragment.newInstance()
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

    fun setValue(){
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val rootRef = database.getReference()
        Log.d("DatabaseUtil", "Database init...")
        Log.d("DatabaseUtil", rootRef.toString())
        rootRef.child("gobishop-bcd0f-default-rtdb:").setValue("Hello, World!")
            .addOnSuccessListener {
                Log.d("DatabaseUtil", "Success")
            }.addOnFailureListener {
                Log.d("DatabaseUtil", it.message.toString())
            }
        //        val userTest = User("Grindewald", "hhah@gmail.com")

    }
}