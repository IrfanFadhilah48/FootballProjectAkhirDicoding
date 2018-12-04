package com.example.irfan.footballmatchschedule.UI.DetailPlayerUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseTeamPlayer
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPlayerPresenter(private val view: DetailPlayerView, private val apiRepository: ApiRepository,
                            private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getPlayerDetail(name: String?){
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayerDetail(name.toString())).await(),
                    ResponseTeamPlayer::class.java)

            data.player?.let { view.showPlayerDetail(it) }
            view.hideLoading()
        }
    }
}