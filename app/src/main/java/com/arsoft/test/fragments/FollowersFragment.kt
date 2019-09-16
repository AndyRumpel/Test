package com.arsoft.test.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arsoft.test.R
import com.arsoft.test.adapters.FollowersRecyclerAdapter
import com.arsoft.test.models.FollowerModel
import com.arsoft.test.presenters.FollowersPresenter
import com.arsoft.test.views.FollowersView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import org.jetbrains.annotations.Nullable
import kotlin.collections.ArrayList

class FollowersFragment: MvpAppCompatFragment(), FollowersView {
    @InjectPresenter
    lateinit var followerPresenter: FollowersPresenter

    private lateinit var followersRecycler: RecyclerView
    private lateinit var followersAdapter: FollowersRecyclerAdapter
    private lateinit var cpvFollowers: CircularProgressView
    private lateinit var txtNoItems: TextView

    companion object {
        fun getInstance(): FollowersFragment {
            return FollowersFragment().apply {
                this.arguments = Bundle()
            }
        }
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mView = inflater.inflate(R.layout.fragment_followers, container, false)
        followersRecycler = mView.findViewById(R.id.followers_recycler)
        cpvFollowers = mView.findViewById(R.id.cpv_followers)
        txtNoItems = mView.findViewById(R.id.followers_no_items)

        followerPresenter.loadFollowers()
        followersAdapter = FollowersRecyclerAdapter()

        followersRecycler.layoutManager = LinearLayoutManager(activity, OrientationHelper.VERTICAL, false)
        followersRecycler.adapter = followersAdapter
        return mView
    }

    // View implementation

    override fun showError(textResource: Int) {
        txtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        followersRecycler.visibility = View.GONE
        cpvFollowers.visibility = View.GONE
        txtNoItems.visibility = View.VISIBLE
    }

    override fun setupFollowers(followersList: ArrayList<FollowerModel>) {
        followersRecycler.visibility = View.VISIBLE
        txtNoItems.visibility = View.GONE

        followersAdapter.setupFollowers(followersList)
    }

    override fun startLoading() {
        followersRecycler.visibility = View.GONE
        txtNoItems.visibility = View.GONE
        cpvFollowers.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpvFollowers.visibility = View.GONE
    }

    override fun runAnimation() {
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        followersRecycler.layoutAnimation = controller
        followersRecycler.adapter?.notifyDataSetChanged()
        followersRecycler.scheduleLayoutAnimation()
    }
}
