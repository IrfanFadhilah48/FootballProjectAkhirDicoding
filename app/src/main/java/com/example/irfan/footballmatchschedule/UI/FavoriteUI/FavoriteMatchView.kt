package com.example.irfan.footballmatchschedule.UI.FavoriteUI

import com.example.irfan.footballmatchschedule.Model.EventsMatches

interface FavoriteMatchView{
    fun showLoading()
    fun hideLoading()
    fun displayFavoriteMatch(data: List<EventsMatches>)
}