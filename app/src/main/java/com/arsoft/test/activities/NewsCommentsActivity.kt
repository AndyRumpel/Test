package com.arsoft.test.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arsoft.test.R
import com.arsoft.test.adapters.NewsCommentsRecyclerAdapter
import com.arsoft.test.models.NewsCommentModel
import com.arsoft.test.presenters.NewsCommentsPresenter
import com.arsoft.test.views.CommentsView
import com.bumptech.glide.Glide
import com.github.rahatarmanahmed.cpv.CircularProgressView
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsCommentsActivity : MvpAppCompatActivity(), CommentsView {

    @InjectPresenter
    lateinit var newsCommentsPresenter: NewsCommentsPresenter

    private lateinit var newsCommentsRecycler: RecyclerView
    private lateinit var newsCommentsAdapter: NewsCommentsRecyclerAdapter
    private lateinit var postAvatar: CircleImageView

    private lateinit var postNameSurname: TextView
    private lateinit var postTime: TextView
    private lateinit var postMainText: TextView
    private lateinit var postLikesCount: TextView
    private lateinit var postCommentsCount: TextView
    private lateinit var postLikeButton: ImageButton
    private lateinit var postShareButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var txtNoItems: TextView
    private lateinit var cpvComments: CircularProgressView
    private lateinit var commentInputTxt: EditText
    private lateinit var commentSendBtn: ImageButton
    private lateinit var scrollView: ScrollView

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_comments)

        postAvatar = findViewById(R.id.civ_post_avatar)
        postNameSurname = findViewById(R.id.post_name_surname)
        postTime = findViewById(R.id.post_time)
        postMainText = findViewById(R.id.post_main_text)
        postLikesCount = findViewById(R.id.post_likes_count)
        postCommentsCount = findViewById(R.id.post_comments_count)
        postLikeButton = findViewById(R.id.post_like_button)
        postShareButton = findViewById(R.id.post_share_button)
        backButton = findViewById(R.id.news_comments_back_btn)
        txtNoItems = findViewById(R.id.comments_no_items)
        cpvComments = findViewById(R.id.cpv_comments)
        commentInputTxt = findViewById(R.id.comments_input_txt)
        commentSendBtn = findViewById(R.id.comments_send_btn)
        newsCommentsRecycler = findViewById(R.id.comments_recycler)
        scrollView = findViewById(R.id.scrollView)

        var isLiked = intent.getBooleanExtra("isLiked", false)
        var likesCount = intent.getIntExtra("likesCount", 0)

        Glide.with(this)
            .load(intent.getStringExtra("avatar"))
            .into(postAvatar)
        postNameSurname.text = "${intent.getStringExtra("name")} ${intent.getStringExtra("surname")}"
        postTime.text = intent.getStringExtra("postingTime")
        postMainText.text = intent.getStringExtra("mainText")
        postLikesCount.text = likesCount.toString()

        newsCommentsPresenter.loadComments()
        newsCommentsRecycler.layoutManager = LinearLayoutManager(this)
        newsCommentsAdapter = NewsCommentsRecyclerAdapter()
        newsCommentsRecycler.adapter = newsCommentsAdapter

        backButton.setOnClickListener {
            onBackPressed()
        }

        postLikeButton.setOnClickListener {
            if (isLiked) {
                likesCount--
                postLikesCount.text = likesCount.toString()
                postLikeButton.setImageResource(R.drawable.ic_favorite_24dp)
                postLikesCount.setTextColor(Color.parseColor("#A0A1A5"))
                isLiked = false
            } else {
                likesCount++
                postLikesCount.text = likesCount.toString()
                postLikeButton.setImageResource(R.drawable.ic_favorite_blue_24dp)
                postLikesCount.setTextColor(Color.parseColor("#2196F3"))
                isLiked = true
            }
            if (likesCount == 0) {
                postLikesCount.visibility = View.GONE
            } else {
                postLikesCount.visibility = View.VISIBLE
            }
        }

        commentSendBtn.setOnClickListener {
            if (commentInputTxt.text.toString() != "") {
                val comment = NewsCommentModel(
                    "Andy",
                    "Rupmel",
                    "https://pp.userapi.com/c849432/v849432958/10839a/wTLY0K_RHWQ.jpg",
                    SimpleDateFormat("HH:mm").format(Date()),
                    commentInputTxt.text.toString(),
                    0,
                    false
                )
                newsCommentsPresenter.sendComment(comment)
                newsCommentsAdapter.notifyDataSetChanged()
                commentInputTxt.setText("")

                scrollView.scrollTo(0, scrollView.bottom)
            }
        }
    }


    // View implementation

    override fun showError(textResource: Int) {
        txtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        newsCommentsRecycler.visibility = View.GONE
        cpvComments.visibility = View.GONE
        txtNoItems.visibility = View.VISIBLE
    }

    override fun setupComments(commentsList: ArrayList<NewsCommentModel>) {
        newsCommentsRecycler.visibility = View.VISIBLE
        txtNoItems.visibility = View.GONE
        newsCommentsAdapter.setupComments(commentsList)
        postCommentsCount.text = newsCommentsAdapter.itemCount.toString()
        postCommentsCount.visibility = View.VISIBLE
    }

    override fun startLoading() {
        newsCommentsRecycler.visibility = View.GONE
        txtNoItems.visibility = View.GONE
        cpvComments.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpvComments.visibility = View.GONE
    }

    override fun runAnimation() {
        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        newsCommentsRecycler.layoutAnimation = controller
        newsCommentsRecycler.adapter?.notifyDataSetChanged()
        newsCommentsRecycler.scheduleLayoutAnimation()
    }
}

