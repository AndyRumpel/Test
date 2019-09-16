package com.arsoft.test.fragments

import android.os.Bundle
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
import com.arsoft.test.adapters.DialogRecyclerAdapter
import com.arsoft.test.models.DialogModel
import com.arsoft.test.presenters.DialogsPresenter
import com.arsoft.test.views.DialogsView
import com.github.rahatarmanahmed.cpv.CircularProgressView

class DialogFragment: MvpAppCompatFragment(), DialogsView {

    @InjectPresenter
    lateinit var dialogsPresenter: DialogsPresenter

    private lateinit var dialogRecycler: RecyclerView
    private lateinit var dialogAdapter: DialogRecyclerAdapter
    private lateinit var cpvDialogs: CircularProgressView
    private lateinit var txtNoItems: TextView

    companion object{
        fun getInstance(): DialogFragment {
            return DialogFragment().apply {
                this.arguments = Bundle()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_dialog, container, false)

        dialogRecycler = mView.findViewById(R.id.dialog_recycler)
        cpvDialogs = mView.findViewById(R.id.cpv_dialogs)
        txtNoItems = mView.findViewById(R.id.dialog_no_items)

        dialogsPresenter.loadDialogs()
        dialogAdapter = DialogRecyclerAdapter()

        dialogRecycler.layoutManager = LinearLayoutManager(activity, OrientationHelper.VERTICAL, false)
        dialogRecycler.adapter = dialogAdapter
        return mView
    }

    // View implementation

    override fun showError(textResource: Int) {
        txtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        dialogRecycler.visibility = View.GONE
        cpvDialogs.visibility = View.GONE
        txtNoItems.visibility = View.VISIBLE
    }

    override fun setupDialogs(dialogsList: ArrayList<DialogModel>) {
        dialogRecycler.visibility = View.VISIBLE
        txtNoItems.visibility = View.GONE

        dialogAdapter.setupDialogs(dialogsList)
    }

    override fun startLoading() {
        dialogRecycler.visibility = View.GONE
        txtNoItems.visibility = View.GONE
        cpvDialogs.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpvDialogs.visibility = View.GONE
    }

    override fun runAnimation() {
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        dialogRecycler.layoutAnimation = controller
        dialogRecycler.adapter?.notifyDataSetChanged()
        dialogRecycler.scheduleLayoutAnimation()
    }
}
