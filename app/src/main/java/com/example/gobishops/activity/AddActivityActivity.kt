
package com.example.gobishops.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.marginTop
import com.example.gobishops.R
import com.example.gobishops.fragment.DatePickerFragment
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.NumericUtil
import kotlinx.android.synthetic.main.activity_add_activity.*

class AddActivityActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener{
    private var uploadCounter: Int = 0
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
        iv_activity_add_add_image.setOnClickListener {
            chooseImage()
        }
    }

    private fun chooseImage(){
        if(uploadCounter >= ConstantUtil.MAX_UPLOAD_IMAGE) return
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), ConstantUtil.SELECT_IMAGE)
    }

    private fun addImageView(imageUri: Uri?){
        val imageView = ImageView(this)
        val size: Int = NumericUtil.pixelToDp(40,this)
        imageView.layoutParams = LinearLayout.LayoutParams(size, size)
        if(null != imageUri){
            imageView.setImageURI(imageUri)
        }else{
            imageView.setImageURI(Uri.parse("android.resource://com.example.gobishops/drawable/ic_back_24.png"))
        }
        ll_activity_add_upload_image.addView(imageView)
        uploadCounter++
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if (requestCode == ConstantUtil.SELECT_IMAGE){
                val selectedImageUri = data!!.data
                addImageView(selectedImageUri)
                Log.d(this.localClassName, selectedImageUri.toString())
            }
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