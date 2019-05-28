package com.arsoft.test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.test.R
import com.arsoft.test.models.NewsModel
import com.arsoft.test.providers.NewsProvider
import com.arsoft.test.views.NewsView

@InjectViewState
class NewsPresenter: MvpPresenter<NewsView>() {

    fun loadNews() {
        viewState.startLoading()
        NewsProvider(this).loadNews()
    }

    fun newsLoaded(newsList: ArrayList<NewsModel>) {
        viewState.endLoading()
        if (newsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(R.string.error_no_items)
        } else {
            viewState.setupNews(newsList)
            viewState.runAnimation()
        }
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource)
    }

}