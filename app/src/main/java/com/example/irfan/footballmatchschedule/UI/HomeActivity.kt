package com.example.irfan.footballmatchschedule.UI

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.FavoriteUI.FavoriteMatchFragment
import com.example.irfan.footballmatchschedule.UI.LastMatchUI.LastMatchFragment
import com.example.irfan.footballmatchschedule.UI.NextMatchUI.NextMatchFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.lastMatch ->{
                    lastMatchFragment(savedInstanceState)
                }
                R.id.nextMatch ->{
                    nextMatchFragment(savedInstanceState)
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
                .replace(R.id.main_containter, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                .commit()
        }

    }

    private fun nextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_containter, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun lastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_containter, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }
}