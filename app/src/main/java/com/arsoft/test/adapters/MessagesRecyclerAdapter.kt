package com.arsoft.test.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arsoft.test.R
import com.arsoft.test.models.MessageModel


class MessagesRecyclerAdapter(
    private val mContext: Context?,
    private val mData: ArrayList<MessageModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MessageTypes.MESSAGE_SEND_VIEW_TYPE.ordinal -> MessagesSendViewHolder(LayoutInflater.from(mContext).inflate(R.layout.message_send_item, parent, false))
            else                                        -> MessagesReceiveViewHolder(LayoutInflater.from(mContext).inflate(R.layout.message_receive_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MessageTypes.MESSAGE_SEND_VIEW_TYPE.ordinal -> {
                val messageSendViewHolder = holder as MessagesSendViewHolder
                messageSendViewHolder.bind(mData[position])
            }
            MessageTypes.MESSAGE_RECEIVE_VIEW_TYPE.ordinal -> {
                val messageReceiveViewType = holder as MessagesReceiveViewHolder
                messageReceiveViewType.bind(mData[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mData[position].isSend) {
            return  MessageTypes.MESSAGE_SEND_VIEW_TYPE.ordinal
        } else {
            return MessageTypes.MESSAGE_RECEIVE_VIEW_TYPE.ordinal
        }

    }

    enum class MessageTypes {
        MESSAGE_SEND_VIEW_TYPE,
        MESSAGE_RECEIVE_VIEW_TYPE
    }

}