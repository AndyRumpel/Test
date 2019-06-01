package com.arsoft.test.views


import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arsoft.test.models.NewsModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsView: MvpView {
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupNews(newsList: ArrayList<NewsModel>)
    fun startLoading()
    fun endLoading()
    fun runAnimation()
}