package com.example.irfan.footballmatchschedule.UI.SearchMatchUI

import com.example.irfan.footballmatchschedule.Model.EventsMatches

interface SearchMatchView{
    fun hideLoading()
    fun showLoading()
    fun showAllMatch(match: List<EventsMatches>)
}