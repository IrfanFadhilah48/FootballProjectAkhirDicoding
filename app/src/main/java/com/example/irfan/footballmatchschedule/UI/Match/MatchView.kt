package com.example.irfan.footballmatchschedule.UI.Match

import com.example.irfan.footballmatchschedule.Model.ResponseLeague

interface MatchView{
    fun showLeagueList(data: ResponseLeague)
}