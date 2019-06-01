package com.arsoft.test.activities

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.*
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.arsoft.test.R
import com.arsoft.test.adapters.TabsPagerFragmentAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f
    private val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f
    private val ALPHA_ANIMATIONS_DURATION = 200
    var imageUri = Uri.parse("https://pp.userapi.com/c849432/v849432958/10839a/wTLY0K_RHWQ.jpg")

    private var mIsTheTitleVisible = false
    private var mIsTheTitleContainerVisible = true

    private lateinit var appbar: AppBarLayout
    private lateinit var collapsing: CollapsingToolbarLayout
    private lateinit var coverImage: ImageView
    private lateinit var frameLayoutTitle: FrameLayout
    private lateinit var linearLayoutTitle: LinearLayout
    private lateinit var toolbar: Toolbar
    private lateinit var textViewTitle: TextView
    private lateinit var avatar: SimpleDraweeView
    private lateinit var tablayout: TabLayout
    private lateinit var adapter: TabsPagerFragmentAdapter
    private lateinit var viewPager: ViewPager


    private fun findViews() {
        appbar = findViewById(R.id.appbar)
        collapsing = findViewById(R.id.collapsing)
        coverImage = findViewById(R.id.imageview_placeholder)
        frameLayoutTitle = findViewById(R.id.framelayout_title)
        linearLayoutTitle = findViewById(R.id.linearlayout_title)
        toolbar = findViewById(R.id.toolbar)
        textViewTitle = findViewById(R.id.textview_title)
        avatar = findViewById(R.id.avatar)
        tablayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewpager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)
        findViews()

        toolbar.title = ""
        appbar.addOnOffsetChangedListener(this)

        setSupportActionBar(toolbar)
        startAlphaAnimation(textViewTitle, 0, View.INVISIBLE)

        Glide.with(this)
            .load(imageUri)
            .apply(RequestOptions().circleCrop())
            .into(avatar)
        coverImage.setImageResource(R.drawable.cover)

        adapter = TabsPagerFragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tablayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(offset).toFloat() / maxScroll.toFloat()

        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(textViewTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE)
                mIsTheTitleVisible = true
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(textViewTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE)
                mIsTheTitleVisible = false
            }
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearLayoutTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE)
                mIsTheTitleContainerVisible = false
            }
        } else {
            if(!mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearLayoutTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE)
                mIsTheTitleContainerVisible = true
            }
        }
    }

    private fun startAlphaAnimation(v: View, duration: Int, visibility: Int) {
        val alphaAnimation = if (visibility == View.VISIBLE)
            AlphaAnimation(0f, 1f)
        else
            AlphaAnimation(1f, 0f)

        alphaAnimation.duration = duration.toLong()
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }
}
