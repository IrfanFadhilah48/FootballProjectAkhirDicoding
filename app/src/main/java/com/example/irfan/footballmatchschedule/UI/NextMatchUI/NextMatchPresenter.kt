package com.example.irfan.footballmatchschedule.UI.NextMatchUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventResponseMatches
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView, private val apiRepository: ApiRepository, private val gson: Gson
                         , private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getLeagueList(){
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllLeagues()).await(),
                    ResponseLeague::class.java
            )
            view.showListLeague(data.leagues.filter { it.strSport == "Soccer" })
        }
    }
    fun getEventNext(idLeague: String?){
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextLeagueTeams(idLeague.toString())).await(),
                EventResponseMatches::class.java
            )
            view.hideLoading()
//            view.showEventList(data.events!!)
            data.events?.let { view.showEventList(it) }
        }

//        GlobalScope.launch(Dispatchers.Main){
//            val data = gson.fromJson(apiRepository
//                .doRequest(TheSportDBApi.getNextLeagueTeams()).await(),
//                EventResponseMatches::class.java
//            )
//            view.hideLoading()
//            view.showEventList(data.events!!)
//        }
//        doAsync {
//            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextLeagueTeams()).await(),
//                EventResponseMatches::class.java
//            )
//
//            uiThread {
//                view.hideLoading()
//                view.showEventList(data.events!!)
//            }
//        }
    }
}