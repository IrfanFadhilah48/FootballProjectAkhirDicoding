package com.example.irfan.footballmatchschedule.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.Favorite.FavoriteFragment
import com.example.irfan.footballmatchschedule.UI.FavoriteUI.FavoriteMatchFragment
import com.example.irfan.footballmatchschedule.UI.NextMatchUI.NextMatchFragment
import kotlinx.android.synthetic.main.activity_home.*
import com.example.irfan.footballmatchschedule.UI.Match.MatchFragment
import com.example.irfan.footballmatchschedule.UI.SearchUI.SearchActivity
import com.example.irfan.footballmatchschedule.UI.Team.TeamFragment
import org.jetbrains.anko.startActivity


class HomeActivity: AppCompatActivity(){

    private var menuSearch: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.lastMatch ->{
                    matchFragment(savedInstanceState)
                }
                R.id.nextMatch ->{
                    teamFragment(savedInstanceState)
                }
                R.id.favorites ->{
                    favoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.lastMatch
    }


    private fun favoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
//                .replace(R.id.main_containter, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                .replace(R.id.main_containter, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }

    }

    private fun teamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                    .replace(R.id.main_containter, TeamFragment(), TeamFragment::class.java.simpleName)
//                .replace(R.id.main_containter, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun matchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                    .replace(R.id.main_containter, MatchFragment(), MatchFragment::class.java.simpleName)
//                .replace(R.id.main_containter, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuSearch = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }R.id.search -> {
                startActivity<SearchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}