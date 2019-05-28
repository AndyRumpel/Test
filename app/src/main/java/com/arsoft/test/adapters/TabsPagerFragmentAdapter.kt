package com.arsoft.test.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpFragment
import com.arsoft.test.fragments.DialogFragment
import com.arsoft.test.fragments.FollowersFragment
import com.arsoft.test.fragments.NewsFragment

class TabsPagerFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    private var tabs = arrayOf("Followers", "News", "Dialogs")




    override fun getItem(position: Int): MvpAppCompatFragment {
        return when (position) {
            0 -> {
                FollowersFragment.getInstance()
            }
            1 -> {
                NewsFragment.getInstance()
            }
            else -> {
                DialogFragment.getInstance()
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    override fun getCount(): Int {
        return tabs.size
    }
}