package com.example.irfan.footballmatchschedule.UI.Team

import android.util.Log
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.Model.ResponseTeam
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(val view: TeamView, val apiRepository: ApiRepository, val gson: Gson,
                    val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun showLeagueList(){
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllLeagues()).await(),
                    ResponseLeague::class.java
            )
            view.showListLeague(data.leagues.filter { it.strSport == "Soccer" })
        }
    }

    fun getTeam(leagueName: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllTeams(leagueName.toString())).await(),
                    ResponseTeam::class.java)

            data.teams?.let { view.showTeamList(it) }
//            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }
}