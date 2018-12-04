package com.example.irfan.footballmatchschedule.UI.FavoriteTeamUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.irfan.footballmatchschedule.Adapter.FavoriteTeamAdapter
import com.example.irfan.footballmatchschedule.DB.database
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.TeamDetailUI.TeamDetailActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavoriteTeamFragment: Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var progressBar: ProgressBar

    private var favorites: MutableList<Team> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressbarFavoriteTeam.visible()
        recylerviewFavoriteTeam.layoutManager = LinearLayoutManager(activity)

        adapter = FavoriteTeamAdapter(favorites){
            toast("Anda memilih ${it.strTeam}")
            requireContext().startActivity<TeamDetailActivity>("id" to it.idTeam, TeamDetailActivity.INTENTDETAIl to it)
        }
        recylerviewFavoriteTeam.adapter = adapter
        showFavorite()
        swipeRefreshTeamFavoriteTeam.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        progressbarFavoriteTeam.invisible()
        context?.database?.use {
            swipeRefreshTeamFavoriteTeam.isRefreshing = false
            favorites.clear()
            val result = select(Team.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<Team>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            Log.e("hasilakhirteam",favorites.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}