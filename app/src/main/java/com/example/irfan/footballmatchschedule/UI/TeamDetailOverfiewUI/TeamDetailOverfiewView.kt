package com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI

import com.example.irfan.footballmatchschedule.Model.Team

interface TeamDetailOverfiewView {
    fun showLoading()
    fun hideLoading()
    fun showOverview(data: List<Team>)
}