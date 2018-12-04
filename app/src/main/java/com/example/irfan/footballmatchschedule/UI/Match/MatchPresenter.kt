package com.example.irfan.footballmatchschedule.UI.Match

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.UI.LastMatchUI.LastMatchView
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(private val view: MatchView, private val apiRepository: ApiRepository, private val gson: Gson
                     , private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getLeagueList(){
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllLeagues()).await(),
                    ResponseLeague::class.java
            )
            view.showLeagueList(data)
        }
    }
}