package com.arsoft.test.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.arsoft.test.adapters.NewsRecyclerAdapter
import com.arsoft.test.models.NewsModel
import com.arsoft.test.presenters.NewsPresenter
import com.arsoft.test.views.NewsView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import org.jetbrains.annotations.Nullable

class NewsFragment: MvpAppCompatFragment(), NewsView{
    @InjectPresenter
    lateinit var newsPresenter: NewsPresenter

    private lateinit var newsRecycler: RecyclerView
    private lateinit var newsAdapter: NewsRecyclerAdapter
    private lateinit var cpvNews: CircularProgressView
    private lateinit var txtNoItems: TextView

    companion object {
        var args = Bundle()
        var fragment = NewsFragment()
        fun getInstance(): NewsFragment {
            fragment.arguments = args
            return fragment
        }
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mView = inflater.inflate(R.layout.fragment_news, container, false)
        newsRecycler = mView.findViewById(R.id.news_recycler)
        cpvNews = mView.findViewById(R.id.cpv_news)
        txtNoItems = mView.findViewById(R.id.news_no_items)

        newsPresenter.loadNews()
        newsAdapter = NewsRecyclerAdapter()

        newsRecycler.layoutManager = LinearLayoutManager(activity, OrientationHelper.VERTICAL, false)
        newsRecycler.adapter = newsAdapter
        return mView
    }


    override fun showError(textResource: Int) {
        txtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        newsRecycler.visibility = View.GONE
        cpvNews.visibility = View.GONE
        txtNoItems.visibility = View.VISIBLE
    }

    override fun setupNews(newsList: ArrayList<NewsModel>) {
        newsRecycler.visibility = View.VISIBLE
        txtNoItems.visibility = View.GONE

        newsAdapter.setupNews(newsList)
    }

    override fun startLoading() {
        newsRecycler.visibility = View.GONE
        txtNoItems.visibility = View.GONE
        cpvNews.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpvNews.visibility = View.GONE
    }

    override fun runAnimation() {
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        newsRecycler.layoutAnimation = controller
        newsRecycler.adapter?.notifyDataSetChanged()
        newsRecycler.scheduleLayoutAnimation()
    }


}
