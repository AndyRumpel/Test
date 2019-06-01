package com.arsoft.test.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.support.v7.widget.Toolbar
import com.arsoft.test.R
import com.facebook.drawee.view.SimpleDraweeView

class ImageBehavior(val context: Context, attr: AttributeSet?): CoordinatorLayout.Behavior<SimpleDraweeView>(context, attr) {

    private var mAvatarMaxSize = 0.0f

    private var mFinalLeftAvatarPadding = 0.0f
    private var mStartXPosition = 0
    private var mStartToolbarPosition = 0.0f

    init{
        bindDimensions()
        mFinalLeftAvatarPadding = context.resources.getDimension(R.dimen.activity_horizontal_margin)

    }

    private fun bindDimensions() {
        mAvatarMaxSize = context.resources.getDimension(R.dimen.image_width)
    }

    private var mStartYPosition = 0
    private var mFinalYPosition = 0
    private var finalHeight = 0
    private var mStartHeight = 0
    private var mFinalXPosition = 0


    override fun layoutDependsOn(parent: CoordinatorLayout, child: SimpleDraweeView, dependency: View): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: SimpleDraweeView, dependency: View): Boolean {
        initProperties(child, dependency)

        val maxScrollDistance = (mStartToolbarPosition - getStatusBarHeight())
        val expandedPercentageFactor = dependency.y / maxScrollDistance
        val distanceYToSubtract = (mStartYPosition - mFinalYPosition) * (1f - expandedPercentageFactor) + child.height / 2
        val distanceXToSubtract = (mStartXPosition - mFinalXPosition) * (1f - expandedPercentageFactor) + child.width / 2
        val heightToSubtract = (mStartHeight - finalHeight) * (1f - expandedPercentageFactor)

        child.y = mStartYPosition - distanceYToSubtract
        child.x = mStartXPosition - distanceXToSubtract

        val lp = child.layoutParams as CoordinatorLayout.LayoutParams
        lp.width = (mStartHeight - heightToSubtract).toInt()
        lp.height = (mStartHeight - heightToSubtract).toInt()
        child.layoutParams = lp

        return true
    }

    @SuppressLint("PrivateResource")
    private fun initProperties(child: SimpleDraweeView, dependency: View) {
        if (mStartYPosition == 0) mStartYPosition = dependency.y.toInt()
        if (mFinalYPosition == 0) mFinalYPosition = dependency.height / 2
        if (mStartHeight == 0) mStartHeight = child.height
        if (finalHeight == 0) finalHeight = context.resources.getDimensionPixelOffset(R.dimen.image_small_width)
        if (mStartXPosition == 0) mStartXPosition = child.x.toInt() + child.width / 2
        if (mFinalXPosition == 0 || mFinalXPosition == mStartXPosition) mFinalXPosition = context.resources.getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + finalHeight / 2
        if (mStartToolbarPosition == 0.0f) mStartToolbarPosition = dependency.y + dependency.height / 2
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceID = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        if (resourceID > 0)
            result = context.resources.getDimensionPixelOffset(resourceID)
        return  result
    }

}