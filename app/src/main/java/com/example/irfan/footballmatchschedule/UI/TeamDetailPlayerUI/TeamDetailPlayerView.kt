package com.example.irfan.footballmatchschedule.UI.TeamDetailPlayerUI

import com.example.irfan.footballmatchschedule.Model.TeamPlayer

interface TeamDetailPlayerView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerTeam(data: List<TeamPlayer>)
}