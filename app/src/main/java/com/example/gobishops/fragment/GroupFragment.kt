package com.example.gobishops.fragment

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.gobishops.R
import com.example.gobishops.adapter.ContactAdapter
import com.example.gobishops.entity.Contact
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.LoginStateUtil
import com.example.gobishops.view.ChatActivity
import com.example.gobishops.view.UserInfoActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.fragment_group.*

/**
 * Created by Yee on 2020/12/26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class GroupFragment : Fragment() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var userName:String
    private val contacts = ArrayList<Contact>()

    companion object{
        fun newInstance(): GroupFragment{
            return GroupFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group, container, false)
    }

    /**
     * Initialize view after the fragment view has been created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    /**
     * View Initialization, including onClickListener()
     */
    private fun initView(){
        val adapter = ContactAdapter(context as Activity, R.layout.view_contact, contacts)
        loadChats(userName)
        Glide.with(requireContext()).asBitmap().load(R.drawable.img_portrait1).into(btn_fragment_group_portrait)
        tv_fragment_group_user_name.text = userName.toUpperCase()
        list_fragment_group.adapter = adapter
        list_fragment_group.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(ConstantUtil.STRING_CONTACT, contacts[position].name.toString())
            startActivity(intent)
        }
        btn_fragment_group_portrait.setOnClickListener {
            startActivity(Intent(context, UserInfoActivity::class.java))
        }
    }

    /**
     * Request for permission and initialize data
     */
    private fun initData(){
        // If not logged in, show nothing
        if(LoginStateUtil.getIsLogin()){
            userName = LoginStateUtil.getUser()!!.userName
        }else{
            return
        }

        // Get Contact permission at runtime
        if (requireContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            getContacts()
            initView()
            return
        }
        AndPermission.with(this)
            .runtime()
            //Permission.READ_CONTACTS, Permission.WRITE_CONTACTS, Permission.GET_ACCOUNTS
            .permission(Permission.Group.CONTACTS)
            .onGranted(Action {
                getContacts()
                initView()
            })
            .onDenied(Action {
                val a = it
                val b = 0
            })
            .start()
    }

    /** Get contacts with content provider**/
    private fun getContacts() {
        val resolver: ContentResolver = requireContext().contentResolver
        val cursor = resolver.query(
            ContactsContract.Contacts.CONTENT_URI, null, null, null,
            null) ?: return

        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNumber = (cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))).toInt()

                if (phoneNumber > 0) {
                    val cursorPhone = requireContext().contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)
                        ?: continue

                    if(cursorPhone.count > 0) {
                        while (cursorPhone.moveToNext()) {
                            val phoneNumValue = cursorPhone.getString(
                                cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            contacts.add(Contact(name, phoneNumValue))
//                            FirebaseUtil.uploadContact(Contact(name, phoneNumValue), name)
                            Log.e("Name ===>",phoneNumValue);
                        }
                    }
                    cursorPhone.close()
                }
            }
        }
        cursor.close()
        return
    }

    /**
     *
     */
    private fun loadChats(uName: String) {
        val chatList: ArrayList<String> = ArrayList()

        val docRef = db.collection("Chat")
        docRef.get().addOnSuccessListener {
            for(item in it){
                if (item.id.contains(userName)){
                    chatList.add(item.id)
                    addChatListener(item.id)
                }
            }

        }.addOnFailureListener {}
    }

    /**
     *
     */
    private fun addChatListener(name: String){
        val docRef = db.collection("Chat").document(name)
        docRef.addSnapshotListener{snapshot, e ->
            if (e != null){
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
            } else {
            }
        }
    }
}


