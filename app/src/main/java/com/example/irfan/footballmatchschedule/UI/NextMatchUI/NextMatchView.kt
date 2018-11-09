package com.example.irfan.footballmatchschedule.UI.NextMatchUI

import com.example.irfan.footballmatchschedule.Model.EventsMatches

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
//    fun showEventList(data: List<EventsNextLeague>)
    fun showEventList(data: List<EventsMatches>)

}