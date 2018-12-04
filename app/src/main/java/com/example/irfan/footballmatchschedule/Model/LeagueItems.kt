package com.example.irfan.footballmatchschedule.Model


data class LeagueItems(val idLeague: String?, val strLeague: String?, val strSport: String?){

    override fun toString(): String {
        return strLeague.toString()
    }
}