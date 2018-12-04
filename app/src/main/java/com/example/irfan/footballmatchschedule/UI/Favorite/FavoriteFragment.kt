package com.example.irfan.footballmatchschedule.UI.Favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.irfan.footballmatchschedule.Adapter.ViewpagerAdapter
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.FavoriteTeamUI.FavoriteTeamFragment
import com.example.irfan.footballmatchschedule.UI.FavoriteUI.FavoriteMatchFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_favorite, container, false)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViewPager(viewpager_favorite)
        tab_favorite.setupWithViewPager(viewpager_favorite)
    }

    private fun setViewPager(viewPager: ViewPager?) {
        val adapter = ViewpagerAdapter(childFragmentManager)
        adapter.addFragment(FavoriteMatchFragment(), "Matches")
        adapter.addFragment(FavoriteTeamFragment(), "Teams")
        viewPager!!.adapter = adapter
    }


}