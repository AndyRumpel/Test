package com.arsoft.test.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arsoft.test.models.DialogModel
import com.arsoft.test.models.NewsCommentModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CommentsView: MvpView {
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupComments(commentsList: ArrayList<NewsCommentModel>)
    fun startLoading()
    fun endLoading()
    fun runAnimation()
}