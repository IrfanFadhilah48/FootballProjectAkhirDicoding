package com.example.irfan.footballmatchschedule.UI.SearchTeamUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Adapter.MatchAdapter
import com.example.irfan.footballmatchschedule.Adapter.TeamsAdapter
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.SearchMatchUI.SearchMatchPresenter
import com.example.irfan.footballmatchschedule.UI.TeamDetailUI.TeamDetailActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast

class SearchTeamFragment: Fragment(), SearchTeamView{

    private lateinit var adapter: TeamsAdapter
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var teams: MutableList<Team> = mutableListOf()

    private lateinit var name: String

    companion object {

        fun newInstanceState(name: String): SearchTeamFragment{
            val fragment = SearchTeamFragment()
            val args = Bundle()
            args.putString("name", name)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_team_search, container, false)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        name = arguments?.getString("name")!!

        presenter = SearchTeamPresenter(this, ApiRepository(), Gson())
        presenter.getAllTeams(name)

        recyclerViewTeamSearch.layoutManager = LinearLayoutManager(activity)
        adapter = TeamsAdapter(teams){
            toast("${it.strTeam}")
            requireContext().startActivity<TeamDetailActivity>("id" to it.idTeam, TeamDetailActivity.INTENTDETAIl to it)
        }
        recyclerViewTeamSearch.adapter = adapter

    }

    override fun showLoading() {
//        progressBarTeamSearch.visible()
    }

    override fun hideLoading() {
//        progressBarTeamSearch.invisible()
    }

    override fun showAllTeams(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
//        var parse = data.filter { it. }
    }
}