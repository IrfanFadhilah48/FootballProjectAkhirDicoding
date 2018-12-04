package com.example.irfan.footballmatchschedule.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter

class ViewpagerAdapter(manager: FragmentManager): FragmentStatePagerAdapter(manager){
//    FragmentPagerAdapter
    val listFragment = ArrayList<Fragment>()
    val titleListFragment = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleListFragment[position]
    }

    override fun getItemPosition(obj: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun addFragment(fragment: Fragment, title: String){
        listFragment.add(fragment)
        titleListFragment.add(title)
    }
}