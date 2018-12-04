package com.example.irfan.footballmatchschedule.UI.DetailPlayerUI

import com.example.irfan.footballmatchschedule.Model.TeamPlayer

interface DetailPlayerView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<TeamPlayer>)
}