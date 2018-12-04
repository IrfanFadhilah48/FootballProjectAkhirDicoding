package com.example.irfan.footballmatchschedule.UI.LastMatchUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventResponseMatches
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(private val view: LastMatchView, private val apiRepository: ApiRepository, private val gson: Gson
                         , private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    private val soccer: MutableList<LeagueItems> = mutableListOf()
    fun getLeagueList(){
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllLeagues()).await(),
                    ResponseLeague::class.java
            )
            view.showLeagueList(data.leagues.filter { it.strSport == "Soccer" })
        }
    }

    fun getEventsLast(idString: String?){
        view.showLoading()

        GlobalScope.launch(context.main){
//            delay(20000)
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPrevLeagueTeams(idString.toString())).await(),
                EventResponseMatches::class.java
            )
            data.events?.let { view.showEventList(it) }
            view.hideLoading()
        }

//        doAsync {
//            val data = gson.fromJson(apiRepository
//                .doRequest(TheSportDBApi.getPrevLeagueTeams()),
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