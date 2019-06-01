package com.arsoft.test.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.arsoft.test.R
import com.arsoft.test.adapters.MessagesRecyclerAdapter
import com.arsoft.test.models.MessageModel
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

class MessagesActivity : AppCompatActivity() {

    private lateinit var messagesRecycler: RecyclerView
    private lateinit var messagesList: ArrayList<MessageModel>
    private lateinit var messagesAdapter: MessagesRecyclerAdapter

    private lateinit var nameSurnameTextView: TextView
    private lateinit var avatarCiv: CircleImageView
    private lateinit var sendButton: ImageButton
    private lateinit var emojiButton: ImageButton
    private lateinit var inputText: EditText
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        nameSurnameTextView = findViewById(R.id.message_name_surname)
        avatarCiv = findViewById(R.id.message_avatar)
        sendButton = findViewById(R.id.msg_send_btn)
        emojiButton = findViewById(R.id.msg_emoji_btn)
        inputText = findViewById(R.id.message_input_txt)
        backButton = findViewById(R.id.messages_back_btn)
        messagesRecycler = findViewById(R.id.messages_recycler)

        nameSurnameTextView.text = "${intent.getStringExtra("name")} ${intent.getStringExtra( "surname")}"
        Glide.with(this).load(intent.getStringExtra("avatar")).into(avatarCiv)

        messagesList = intent.getParcelableArrayListExtra("messagesList")
        messagesRecycler.layoutManager = LinearLayoutManager(applicationContext)
        messagesAdapter = MessagesRecyclerAdapter(this, messagesList)
        messagesRecycler.adapter = messagesAdapter

        sendButton.setOnClickListener {
            if (inputText.text.toString() != "") {
                sendMessage(inputText.text.toString())
                messagesAdapter.notifyDataSetChanged()
                messagesRecycler.smoothScrollToPosition(messagesList.size)
            }
            inputText.setText("")
        }

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun sendMessage(messageText: String) {
        messagesList.add(MessageModel(messageText, SimpleDateFormat("HH:mm").format(Date()), true))
    }
}
