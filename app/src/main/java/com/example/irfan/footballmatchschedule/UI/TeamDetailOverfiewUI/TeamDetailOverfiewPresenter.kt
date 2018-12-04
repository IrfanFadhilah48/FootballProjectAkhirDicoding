package com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseTeam
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailOverfiewPresenter(private val view: TeamDetailOverfiewView, private val apiRepository: ApiRepository,
                                  private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getOverviewDetail(idString: String?){

        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsDetail(idString.toString())).await(),
                ResponseTeam::class.java)

            view.showOverview(data.teams)
            view.hideLoading()
        }
    }
}