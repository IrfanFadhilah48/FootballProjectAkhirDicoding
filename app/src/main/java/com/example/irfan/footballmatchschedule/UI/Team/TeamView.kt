package com.example.irfan.footballmatchschedule.UI.Team

import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.Model.Team

interface TeamView{
    fun showLoading()
    fun hideLoading()
//    fun showListLeague(data: ResponseLeague)
    fun showListLeague(data: List<LeagueItems>)
    fun showTeamList(data: List<Team>)
}