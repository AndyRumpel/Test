package com.arsoft.test.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arsoft.test.models.FollowerModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FollowersView: MvpView{
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupFollowers(followersList: ArrayList<FollowerModel>)
    fun startLoading()
    fun endLoading()
    fun runAnimation()
}