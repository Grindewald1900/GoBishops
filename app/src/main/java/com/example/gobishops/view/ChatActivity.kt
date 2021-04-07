package com.example.gobishops.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.gobishops.R
import com.example.gobishops.adapter.ChatAdapter
import com.example.gobishops.entity.ChatMessage
import com.example.gobishops.utils.ConstantUtil
import com.example.gobishops.utils.LoginStateUtil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.activity_chat.*
import java.sql.Timestamp


class ChatActivity : AppCompatActivity() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var chatList : ListView
    private val chats = ArrayList<ChatMessage>()
    private lateinit var contactName : String
    private lateinit var userName : String
    private var chatId :String = ""
    private var isOpen : Boolean = false
    private var startTime : Long = 0
    private var endTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initData()
    }


    /**
     *  Initialize data
     */
    private fun initData(){
        userName = LoginStateUtil.getUser()!!.userName
        contactName = intent.getStringExtra(ConstantUtil.STRING_CONTACT)!!
        findChats()
    }


    /**
     *  Initialize main view
     */
    private fun initView() {
        val adapter = ChatAdapter(this,R.layout.view_chat_item, chats)
        chatList = findViewById(R.id.list_activity_chat)
        chatList.adapter = adapter
        chatList.smoothScrollToPositionFromTop(chats.size - 1, chatList.height - 100, 100)
        chatList.setSelection(chats.size - 1)
        chatList.divider = null
        btn_activity_chat_send_msg.setOnClickListener {
            val msg = et_activity_chat_input.text.toString()
            et_activity_chat_input.text.clear()
            uploadChat(userName, msg)
            startTime = System.currentTimeMillis()
        }
    }


    /**
     * If Chat already exist in Firebase
     */
    private fun findChats() {
        val docRef = db.collection("Chat")
        docRef.get().addOnSuccessListener {
            for(item in it){
                // The document name: chat@yee@Test
                if (item.id.contains(userName) && item.id.contains(contactName)){
                    chatId = item.id
                }
            }
            openChat()
        }.addOnFailureListener {}
    }

    /**
     * Start the chat
     */
    private fun openChat(){
        // If no chat exist, add the chat
        if("" == chatId) chatId = "chat@$userName@$contactName"
        val docRef = db.collection("Chat").document(chatId)
        // Add chat listener only when initialization
        if(!isOpen){
            addChatListener(chatId)
            isOpen = true
        }
        docRef.get().addOnSuccessListener {
            if(it.exists()){
                val str = it.data.toString().substring(1, it.data.toString().length - 1)
                val list:List<String> = str.split(",")
                for (item in list){
                    // msg: message@Username@2020-04-01 = "message"
                    val msg = item.split("=")
                    // list: [0]:message, [1]: Username, [2]: time
                    val preInfo = msg[0].split("@")
                    val message = ChatMessage(preInfo[2], msg[1], preInfo[1])
                    if(!chats.contains(message)){
                        endTime = System.currentTimeMillis()
                        if(startTime.toInt() != 0){
                            val showTxt : String = if(message.msg.length > 5){
                                message.msg.substring(0,5)
                            }else{
                                message.msg
                            }
                            Toast.makeText(this, showTxt + "...\n" + "time to upload:" + (endTime-startTime).toInt() + " ms",Toast.LENGTH_LONG).show()
                        }
                        chats.add(message)
                    }
                }
                initView()
            }else{
                setChat(userName, contactName)
            }
        }.addOnFailureListener {
        }
    }

    /**
     * If no chat exist, add the chat
     */
    private fun setChat(nameUser: String, nameContact:String){
        val time = Timestamp(System.currentTimeMillis())
        val docReference = db.collection("Chat").document("chat@$nameUser@$nameContact")
        val chat: MutableMap<String, Any> = HashMap()
        chat["message@System@$time"] = "Welcome!"

        docReference.set(chat)
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }
    }

    /**
     * Add new message into chat document
     */
    private fun uploadChat(nameUser: String, msg:String){
        val time = Timestamp(System.currentTimeMillis())
        val chat: MutableMap<String, Any> = HashMap()
        chat["message@$nameUser@$time"] = msg

        db.collection("Chat").document(chatId).set(chat, SetOptions.merge())
            .addOnSuccessListener {
                openChat()
            }
            .addOnFailureListener {
            }
    }


    /**
     * Add listener
     */
    private fun addChatListener(name: String) {
        val docRef = db.collection("Chat").document(name)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                openChat()
            }
        }
    }


}