package com.arsoft.test.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.test.R
import com.arsoft.test.models.NewsCommentModel
import com.arsoft.test.providers.NewsCommentsProvider
import com.arsoft.test.views.CommentsView

@InjectViewState
class NewsCommentsPresenter: MvpPresenter<CommentsView>() {

    private val commentsProvider = NewsCommentsProvider(this)

    fun loadComments() {
        viewState.startLoading()
        commentsProvider.loadComments()
    }

    fun commentsLoaded(commentsList: ArrayList<NewsCommentModel>) {
        viewState.endLoading()
        if (commentsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(R.string.error_no_items)
        } else {
            viewState.setupComments(commentsList)
            viewState.runAnimation()
        }
    }

    fun sendComment(comment: NewsCommentModel) {
        commentsProvider.sendComment(comment)
    }

    fun sendCommentsLoaded(commentsList: ArrayList<NewsCommentModel>) {
        viewState.setupComments(commentsList)
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource)
    }

}
