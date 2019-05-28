package com.arsoft.test.adapters


import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsoft.test.R
import com.arsoft.test.models.FollowerModel
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.followers_recycler_item.view.*


class FollowersRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mFollowersList = ArrayList<FollowerModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.followers_recycler_item, parent, false)
        return FollowersViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowersViewHolder) {
            holder.bind(mFollowersList[position])
        }
    }

    override fun getItemCount(): Int {
        return mFollowersList.size
    }

    fun setupFollowers(followersList: ArrayList<FollowerModel>) {
        mFollowersList.clear()
        mFollowersList.addAll(followersList)
    }

    class FollowersViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name_surname = view.follower_name_surname
        private val avatar = view.follower_avatar
        private val followersCount = view.follower_count

        @SuppressLint("SetTextI18n")
        fun bind(followerModel: FollowerModel) {

            Glide.with(itemView.context)
                .load(followerModel.avatar)
                .into(avatar)
            name_surname.text = "${followerModel.name} ${followerModel.surname}"
            followersCount.text = followerModel.followersCount.toString()
        }

    }

}