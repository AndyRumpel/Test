package com.arsoft.test.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsoft.test.activities.MessagesActivity
import com.arsoft.test.R
import com.arsoft.test.models.DialogModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog_recycler_item.view.*

class DialogRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDialogsList = ArrayList<DialogModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dialog_recycler_item, parent, false)
        return DialogViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mDialogsList.size
    }

    fun setupDialogs(dialogList: ArrayList<DialogModel>) {
        mDialogsList.clear()
        mDialogsList.addAll(dialogList)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DialogViewHolder) {
            holder.bind(mDialogsList[position])
            holder.itemView.setOnClickListener {
                val intent = Intent(it.context, MessagesActivity::class.java)
                intent.putParcelableArrayListExtra("messagesList", mDialogsList[position].messagesList)
                intent.putExtra("name", mDialogsList[position].name)
                intent.putExtra("surname", mDialogsList[position].surname)
                intent.putExtra("avatar", mDialogsList[position].avatar)
                it.context.startActivity(intent)
            }
        }
    }

    class DialogViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name_surname = view.dialog_name_surname
        private val avatar = view.dialog_avatar
        private val lastMessageTime = view.last_msg_time
        private val lastMessageText = view.last_message_text

        fun bind(dialogModel: DialogModel) {
            Glide.with(itemView.context)
                .load(dialogModel.avatar)
                .into(avatar)
            name_surname.text = "${dialogModel.name} ${dialogModel.surname}"
            lastMessageTime.text = dialogModel.messagesList?.last()?.messageTime
            lastMessageText.text = dialogModel.messagesList?.last()?.messageText

        }

    }

}
