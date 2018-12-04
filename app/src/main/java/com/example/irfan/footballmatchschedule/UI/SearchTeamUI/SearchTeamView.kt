package com.example.irfan.footballmatchschedule.UI.SearchTeamUI

import com.example.irfan.footballmatchschedule.Model.Team

interface SearchTeamView{
    fun showLoading()
    fun hideLoading()
    fun showAllTeams(data: List<Team>)
}