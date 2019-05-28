package com.arsoft.test.providers

import android.os.Handler
import com.arsoft.test.models.NewsCommentModel
import com.arsoft.test.presenters.NewsCommentsPresenter
import kotlin.collections.ArrayList

class NewsCommentsProvider(var presenter: NewsCommentsPresenter) {

    private val commentsList = ArrayList<NewsCommentModel>()

    fun loadComments() {
        Handler().postDelayed({
            commentsList.add(
                NewsCommentModel(
                    "Avdotya",
                    "Raskolnkova",
                    "https://pp.userapi.com/c851216/v851216260/102256/YUsMBVCLGIU.jpg",
                    "13:10",
                    "Лол",
                    0,
                    false
                )
            )
            commentsList.add(
                NewsCommentModel(
                    "Max",
                    "Redkin",
                    "https://pp.userapi.com/c844321/v844321294/8b2cf/QvUtuwmVW7E.jpg",
                    "14:40",
                    "Смешно",
                    0,
                    false
                )
            )
            commentsList.add(
                NewsCommentModel(
                    "Ilnur",
                    "Sadreev",
                    "https://pp.userapi.com/c627919/v627919459/1cd2c/f5MlNluwak0.jpg",
                    "15:13",
                    "ВЗЛОМ ЖОПЫ",
                    0,
                    false
                )
            )


            presenter.commentsLoaded(commentsList)
        }, 2000)

    }

    fun sendComment(comment: NewsCommentModel){
            commentsList.add(comment)
            presenter.sendCommentsLoaded(commentsList)
    }
}
