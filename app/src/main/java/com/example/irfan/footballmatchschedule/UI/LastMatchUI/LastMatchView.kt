package com.example.irfan.footballmatchschedule.UI.LastMatchUI

import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague

interface LastMatchView{
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<LeagueItems>)
    fun showEventList(data: List<EventsMatches>)
}