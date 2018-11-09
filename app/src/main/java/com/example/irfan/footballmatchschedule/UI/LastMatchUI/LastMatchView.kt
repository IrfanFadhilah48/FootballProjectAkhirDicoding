package com.example.irfan.footballmatchschedule.UI.LastMatchUI

import com.example.irfan.footballmatchschedule.Model.EventsMatches

interface LastMatchView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<EventsMatches>)
}