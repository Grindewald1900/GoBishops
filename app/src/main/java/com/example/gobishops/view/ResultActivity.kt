package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gobishops.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        btn_activity_result_back.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}