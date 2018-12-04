package com.example.irfan.footballmatchschedule.UI.NextMatchUI

import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showListLeague(data: List<LeagueItems>)
    fun showEventList(data: List<EventsMatches>)

}