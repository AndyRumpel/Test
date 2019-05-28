package com.arsoft.test.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsoft.test.R
import com.arsoft.test.models.NewsCommentModel
import com.arsoft.test.views.CommentsView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.comment_recycler_item.view.*

class NewsCommentsRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var mData = ArrayList<NewsCommentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsCommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_recycler_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val newsCommentsViewHolder = holder as NewsCommentsViewHolder
        newsCommentsViewHolder.bind(mData[position])
    }

    fun setupComments(commentsList: ArrayList<NewsCommentModel>) {
        mData.clear()
        mData.addAll(commentsList)
    }

    class NewsCommentsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(commentModel: NewsCommentModel) {
            Glide.with(itemView.context)
                .load(commentModel.avatar)
                .into(itemView.comment_avatar)
            itemView.comment_name_surname.text = "${commentModel.name} ${commentModel.surname}"
            itemView.comment_time.text = commentModel.commentTime
            if (commentModel.likesCount == 0) {
                itemView.comment_likes_count.visibility = View.GONE
            } else {
                itemView.comment_likes_count.visibility = View.VISIBLE
            }
            itemView.comment_main_text.text = commentModel.commentText
            itemView.comment_like_btn.setOnClickListener {
                if (commentModel.isLiked) {
                    commentModel.likesCount--
                    itemView.comment_likes_count.text = commentModel.likesCount.toString()
                    itemView.comment_like_btn.setImageResource(R.drawable.ic_favorite_24dp)
                    itemView.comment_likes_count.setTextColor(Color.parseColor("#A0A1A5"))
                    commentModel.isLiked = false
                } else {
                    commentModel.likesCount++
                    itemView.comment_likes_count.text = commentModel.likesCount.toString()
                    itemView.comment_like_btn.setImageResource(R.drawable.ic_favorite_blue_24dp)
                    itemView.comment_likes_count.setTextColor(Color.parseColor("#2196F3"))
                    commentModel.isLiked = true
                }
                if (commentModel.likesCount == 0) {
                    itemView.comment_likes_count.visibility = View.GONE
                } else {
                    itemView.comment_likes_count.visibility = View.VISIBLE
                }
            }
        }
    }
}