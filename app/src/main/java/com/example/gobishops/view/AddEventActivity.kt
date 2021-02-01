
package com.example.gobishops.view

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.gobishops.R
import com.example.gobishops.contract.AddEventContract
import com.example.gobishops.entity.Event
import com.example.gobishops.fragment.DatePickerFragment
import com.example.gobishops.presenter.AddEventPresenter
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.NumericUtil
import com.example.gobishops.utils.StorageUtil
import kotlinx.android.synthetic.main.activity_add_event.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class AddEventActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, AddEventContract.View{
    private var presenter: AddEventContract.Presenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        presenter = AddEventPresenter(this)
    }

    /**
     * View Initialization, including onClickListener()
     */
    override fun initView(){
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
            val dateFragment = DatePickerFragment(this)
            dateFragment.show(supportFragmentManager, "datePicker")
        }
        iv_activity_add_add_image.setOnClickListener {
            presenter!!.chooseImage(getString(R.string.hint_max_image_upload))
        }
        btn_activity_add_release.setOnClickListener {
            presenter!!.releaseEvent()
        }
        et_activity_add_title.setOnClickListener {
//            presenter!!.clickTitle()
        }
        tv_activity_add_title.setOnClickListener {
            presenter!!.clickTitle(true)
        }
        et_activity_add_title.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus){
                tv_activity_add_title.text = et_activity_add_title.text
                presenter!!.clickTitle(false)
            }
        }
    }

    /**
     * Choose image from photo gallery
     */
    override fun selectImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), ConstantUtil.SELECT_IMAGE)
    }

    override fun setTitleEditable(isEditable: Boolean) {
        et_activity_add_title.visibility = if(isEditable) View.VISIBLE else View.INVISIBLE
        tv_activity_add_title.visibility = if(isEditable) View.INVISIBLE else View.VISIBLE
    }

    /**
     * Set the content of event title
     */
    override fun refreshTitle(title: String) {
        tv_activity_add_title.text = title
    }

    /**
     * Refresh the TextView of date selector
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun refreshDate(year: Int, month: Int, dayOfMonth: Int) {
        et_activity_add_time.text = "$year : $month : $dayOfMonth"
        Log.d("CalendarResult", "activity$year$month$dayOfMonth")
        presenter?.setEventDate(LocalDate.of(year, month, dayOfMonth))
    }

    /**
     * Add thumbnail to LinearLayout dynamically
     */
    override fun addImageView(imageUri: Uri?){
        val imageView = ImageView(this)
        val size: Int = NumericUtil.pixelToDp(40,this)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(size, size)
        params.setMargins(0,0,20,0)
        imageView.layoutParams = params
        imageView.scaleType = ImageView.ScaleType.CENTER

        // If image is selected
        if(null != imageUri){
            // ImageID: combined of event ID and image counter, e.g. 42837faf-d8dc-42e6-9fce-8fe70afb9553*1
            val imageID = presenter!!.getEventID() + "*"+presenter!!.getUploadCounter().toString()
            imageView.setImageURI(imageUri)
            presenter!!.addImageSet(imageID)
            StorageUtil.uploadImage(imageUri, imageID, this)
        }else{
            imageView.setImageURI(Uri.parse("android.resource://com.example.gobishops/drawable/ic_back_24.png"))
        }
        ll_activity_add_upload_image.addView(imageView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getInputData(): Event {
        var set: ArrayList<String> = ArrayList()
        set.add("11111111111111111")
        set.add("222222222222222")
        set.add("3333333333")
        return Event()
    }


    /**
     * Deal with callback from photo gallery
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if (requestCode == ConstantUtil.SELECT_IMAGE){
                val selectedImageUri = data!!.data
                presenter!!.addImageToView(selectedImageUri)
                Log.d(this.localClassName, selectedImageUri.toString())
            }
        }
    }
    /**
     * Show a short toast
     */
    override fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}