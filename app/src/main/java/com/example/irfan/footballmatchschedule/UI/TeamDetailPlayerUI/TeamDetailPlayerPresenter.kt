package com.example.irfan.footballmatchschedule.UI.TeamDetailPlayerUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseTeamPlayer
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPlayerPresenter(private val view: TeamDetailPlayerView, private val apiRepository: ApiRepository,
                                private val gson: Gson,
                                private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getPlayerTeam(idTeam: String?){
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamPlayerDetail(idTeam.toString())).await(),
                    ResponseTeamPlayer :: class.java)

            view.hideLoading()
            data.player?.let { view.showPlayerTeam(it) }
        }
    }
}