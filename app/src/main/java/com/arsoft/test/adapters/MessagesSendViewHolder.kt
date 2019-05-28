package com.arsoft.test.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.arsoft.test.models.MessageModel
import kotlinx.android.synthetic.main.message_send_item.view.*

class MessagesSendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(messageModel: MessageModel) {
        itemView.msg_send_txt.text = messageModel.messageText
        itemView.msg_send_time.text = messageModel.messageTime
    }
}