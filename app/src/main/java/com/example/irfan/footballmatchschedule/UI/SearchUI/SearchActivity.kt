package com.example.irfan.footballmatchschedule.UI.SearchUI

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import com.example.irfan.footballmatchschedule.Adapter.ViewpagerAdapter
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.SearchMatchUI.SearchMatchFragment
import com.example.irfan.footballmatchschedule.UI.SearchTeamUI.SearchTeamFragment
import kotlinx.android.synthetic.main.activity_match_search.*


class SearchActivity: AppCompatActivity(){

    private lateinit var searchView: SearchView
    private var menuSearch: Menu? = null
    private lateinit var adapter: ViewpagerAdapter
    private lateinit var parse: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)

        viewpager_search.isSaveFromParentEnabled = false
        tab_search.setupWithViewPager(viewpager_search)
    }

    private fun setViewPager(viewPager: ViewPager) {
        adapter = ViewpagerAdapter(supportFragmentManager)
        adapter.addFragment(SearchMatchFragment.newInstance(parse), "Matchs")
        adapter.addFragment(SearchTeamFragment.newInstanceState(parse), "Teams")
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val search: MenuItem? = menu.findItem(R.id.searchActivity)
        searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
//                parse = query.toLowerCase()
//                setViewPager(viewpager_search)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                parse = query?.toLowerCase().toString()
                setViewPager(viewpager_search)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

}