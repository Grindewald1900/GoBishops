
package com.example.gobishops.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import com.example.gobishops.R
import com.example.gobishops.fragment.DatePickerFragment
import kotlinx.android.synthetic.main.activity_add_activity.*

class AddActivityActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activity)
        initView()
    }

    private fun initView(){
        val spinnerPermission: Spinner = findViewById(R.id.spinner_activity_add_permission)
        val spinnerType: Spinner = findViewById(R.id.spinner_activity_add_type)
        ArrayAdapter.createFromResource(this,R.array.activity_permission, R.layout.spinner_item_simple).also {
            it.setDropDownViewResource(R.layout.spinner_margin_20)
            spinnerPermission.adapter = it
        }
        ArrayAdapter.createFromResource(this, R.array.activity_type, R.layout.spinner_item_simple).also {
            it.setDropDownViewResource(R.layout.spinner_margin_20)
            spinnerType.adapter = it
        }
        btn_activity_add_back.setOnClickListener {
            onBackPressed()
            finish()
        }

        et_activity_add_time.setOnClickListener {
            val dateFragment = DatePickerFragment()
            dateFragment.show(supportFragmentManager, "datePicker")
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        et_activity_add_time.text = "$year : $month : $dayOfMonth"
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}