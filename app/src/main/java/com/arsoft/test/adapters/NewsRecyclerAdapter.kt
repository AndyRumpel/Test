package com.arsoft.test.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsoft.test.R
import com.arsoft.test.activities.NewsCommentsActivity
import com.arsoft.test.models.NewsModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_recycler_item.view.*

class NewsRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mNewsList = ArrayList<NewsModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.news_recycler_item, parent, false)
        return NewsViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsViewHolder) {
            holder.bind(mNewsList[position])
            holder.itemView.setOnClickListener {
                val intent = Intent(it.context, NewsCommentsActivity::class.java)
                intent.putExtra("name", mNewsList[position].name)
                intent.putExtra("surname", mNewsList[position].surname)
                intent.putExtra("isLiked", mNewsList[position].isLiked)
                intent.putExtra("likesCount", mNewsList[position].likesCount)
                intent.putExtra("postingTime", mNewsList[position].postingTime)
                intent.putExtra("mainText", mNewsList[position].main_txt)
                intent.putExtra("avatar", mNewsList[position].avatar)
                intent.putExtra("commentsCount", mNewsList[position].commentsCount)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return mNewsList.size
    }

    fun setupNews(newsList: ArrayList<NewsModel>) {
        mNewsList.clear()
        mNewsList.addAll(newsList)
    }


    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name_surname = view.news_name_surname
        private val avatar = view.civ_news
        private val postingTime = view.news_posting_time
        private val mainText = view.news_main_text
        private val likeButton = view.like_button
        private val likesCount = view.likes_count


        @SuppressLint("SetTextI18n")
        fun bind(newsModel: NewsModel) {
            Glide.with(itemView.context)
                .load(newsModel.avatar)
                .into(avatar)
            name_surname.text = "${newsModel.name} ${newsModel.surname}"
            postingTime.text = newsModel.postingTime
            mainText.text = newsModel.main_txt
            likesCount.text = newsModel.likesCount.toString()
            if (newsModel.likesCount == 0) {
                likesCount.visibility = View.GONE
            } else {
                likesCount.visibility = View.VISIBLE
            }
            likeButton.setOnClickListener {
                if (newsModel.isLiked) {
                    newsModel.likesCount--
                    likesCount.text = newsModel.likesCount.toString()
                    likeButton.setImageResource(R.drawable.ic_favorite_24dp)
                    likesCount.setTextColor(Color.parseColor("#A0A1A5"))
                    newsModel.isLiked = false
                } else {
                    newsModel.likesCount++
                    likesCount.text = newsModel.likesCount.toString()
                    likeButton.setImageResource(R.drawable.ic_favorite_blue_24dp)
                    likesCount.setTextColor(Color.parseColor("#2196F3"))
                    newsModel.isLiked = true
                }
                if (newsModel.likesCount == 0) {
                    likesCount.visibility = View.GONE
                } else {
                    likesCount.visibility = View.VISIBLE
                }
            }

        }
    }
}