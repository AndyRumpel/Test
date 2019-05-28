package com.arsoft.test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.test.R
import com.arsoft.test.models.FollowerModel
import com.arsoft.test.providers.FollowersProvider
import com.arsoft.test.views.FollowersView

@InjectViewState
class FollowersPresenter: MvpPresenter<FollowersView>() {

    fun loadFollowers() {
        viewState.startLoading()
        FollowersProvider(this).loadFollowers()
    }

    fun followersLoaded(followersList: ArrayList<FollowerModel>) {
        viewState.endLoading()
        if (followersList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(R.string.error_no_items)
        } else {
            viewState.setupFollowers(followersList)
            viewState.runAnimation()
        }
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource)
    }

}