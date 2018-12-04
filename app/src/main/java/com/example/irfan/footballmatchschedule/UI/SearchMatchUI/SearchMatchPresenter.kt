package com.example.irfan.footballmatchschedule.UI.SearchMatchUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventResponseMatches
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.ResponseEventMatchSearch
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(private val view: SearchMatchView, private val apiRepository: ApiRepository,
                           private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getAll(name:String?){
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchMatch(name.toString())).await(),
                ResponseEventMatchSearch::class.java
            )

            view.hideLoading()
            view.showAllMatch(data.event!!)
        }
    }
}