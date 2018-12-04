package com.example.irfan.footballmatchschedule.UI.Team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Adapter.TeamsAdapter
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.TeamDetailUI.TeamDetailActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast

class TeamFragment: Fragment(), TeamView{

    private lateinit var presenter: TeamPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var adapter: TeamsAdapter
    private lateinit var league: LeagueItems

    private var teams: MutableList<Team> = mutableListOf()
    private var dataSpinner: MutableList<LeagueItems> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_team, container, false)
        recyclerView = viewRoot.find(R.id.recylerviewTeam)
        progressBar = viewRoot.find(R.id.progressbarTeam)
        spinner = viewRoot.find(R.id.spinnerTeam)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        return viewRoot
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        presenter = TeamPresenter(this, ApiRepository(), Gson())

        presenter.showLeagueList()
        adapter = TeamsAdapter(teams){
            toast("Anda memilih ${it.strTeam}")
            context?.startActivity<TeamDetailActivity>("id" to it.idTeam, TeamDetailActivity.INTENTDETAIl to it)
        }

        recyclerView.adapter = adapter

    }

    override fun showListLeague(data: List<LeagueItems>) {
        dataSpinner.addAll(data)

        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getTeam(dataSpinner.get(position).strLeague)
            }
        }
    }

//    override fun showListLeague(data: ResponseLeague) {
//        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data.leagues)
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                league = spinner.selectedItem as LeagueItems
//
//                presenter.getTeam(league.strLeague.toString())
//            }
//        }
//    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        Log.e("datanya", data.toString())
    }

}