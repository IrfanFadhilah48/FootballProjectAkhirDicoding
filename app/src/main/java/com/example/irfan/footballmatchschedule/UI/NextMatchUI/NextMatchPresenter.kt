package com.example.irfan.footballmatchschedule.UI.NextMatchUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventResponseMatches
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView, private val apiRepository: ApiRepository, private val gson: Gson){

    fun getEventLast(){
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextLeagueTeams()),
                EventResponseMatches::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events!!)
            }
        }
    }
}