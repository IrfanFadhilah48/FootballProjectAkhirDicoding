package com.example.irfan.footballmatchschedule.UI.SearchTeamUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseTeam
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(private val view: SearchTeamView, private val apiRepository: ApiRepository,
                          private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getAllTeams(name: String?){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchTeams(name.toString())).await(),
                ResponseTeam::class.java)

            view.hideLoading()
            data.teams?.let { view.showAllTeams(it) }
        }
    }
}