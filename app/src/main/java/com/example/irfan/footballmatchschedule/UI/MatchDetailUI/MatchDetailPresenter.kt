package com.example.irfan.footballmatchschedule.UI.MatchDetailUI

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.provider.SyncStateContract.Helpers.insert
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.DB.database
import com.example.irfan.footballmatchschedule.Model.*
import com.example.irfan.footballmatchschedule.Utils.CoroutineContextProvider
import com.example.irfan.footballmatchschedule.Utils.DateTimeConverter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.nio.file.Files.delete

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val dataHomeTeam = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamsDetail(idHomeTeam.toString())).await(),
                ResponseTeamItems::class.java
            )

            val dataAwayTeam = gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.getTeamsDetail(idAwayTeam.toString())).await(),
                ResponseTeamItems::class.java
            )

            view.showTeamDetails(dataHomeTeam.teams!!, dataAwayTeam.teams!!)
            view.hideLoading()
        }
    }

    fun getMatchDetail(idEvent: String?) {
//        view.showLoading()
        GlobalScope.launch(context.main){
            delay(1000)
            val dataDetailMatch = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(idEvent.toString())).await(),
                ResponseMatchDetail::class.java
            )
            view.showMatchDetails(dataDetailMatch.events!!)
//            view.hideLoading()
        }
//        doAsync {
//            val dataDetailMatch = gson.fromJson(
//                apiRepository.doRequest(TheSportDBApi.getMatchDetail(idEvent.toString())),
//                ResponseMatchDetail::class.java
//            )
//
//            uiThread {
//                view.hideLoading()
//                view.showMatchDetails(dataDetailMatch.events!!)
//            }
//        }
    }

    //        doAsync {
//            val dataHomeTeam = gson.fromJson(
//                apiRepository.doRequest(TheSportDBApi.getTeamsDetail(idHomeTeam.toString())),
//                ResponseTeamItems::class.java
//            )
//
//            val dataAwayTeam = gson.fromJson(
//                apiRepository.doRequest(TheSportDBApi.getTeamsDetail(idAwayTeam.toString())),
//                ResponseTeamItems::class.java
//            )
//
//            uiThread {
//                view.hideLoading()
//                view.showTeamDetails(dataHomeTeam.teams!!, dataAwayTeam.teams!!)
//            }
//        }
    fun addFavorite(context: Context, match: EventsMatches) {
        try {
            context.database.use {
                insert(
                    EventsMatches.TABLE_FAVORITES,
                    EventsMatches.ID_EVENT to match.idEvent,
                    EventsMatches.DATE to match.dateEvent,
                    EventsMatches.TIME_EVENT to match.strTime,

                    EventsMatches.HOME_ID to match.idHomeTeam,
                    EventsMatches.HOME_TEAM to match.strHomeTeam,
                    EventsMatches.HOME_SCORE to match.intHomeScore,
                    EventsMatches.HOME_GOAL_DETAILS to match.strHomeGoalDetails,
                    EventsMatches.HOME_SHOTS to match.intHomeShots,
//                    Favorite.HOME_FORMATION to match.strHomeFormation,
                    EventsMatches.HOME_LINEUP_GOALKEEPER to match.strHomeLineupGoalkeeper,
                    EventsMatches.HOME_LINEUP_DEFENSE to match.strHomeLineupDefense,
                    EventsMatches.HOME_LINEUP_MIDFIELD to match.strHomeLineupMidfield,
                    EventsMatches.HOME_LINEUP_FORWARD to match.strHomeLineupForward,
                    EventsMatches.HOME_LINEUP_SUBSTITUTES to match.strHomeLineupSubstitutes,

                    EventsMatches.AWAY_ID to match.idAwayTeam,
                    EventsMatches.AWAY_TEAM to match.strAwayTeam,
                    EventsMatches.AWAY_SCORE to match.intAwayScore,
                    EventsMatches.AWAY_GOAL_DETAILS to match.strAwayGoalDetails,
                    EventsMatches.AWAY_SHOTS to match.intAwayShots,
//                    Favorite.AWAY_FORMATION to match.strAwayFormation,
                    EventsMatches.AWAY_LINEUP_GOALKEEPER to match.strAwayLineupGoalkeeper,
                    EventsMatches.AWAY_LINEUP_DEFENSE to match.strAwayLineupDefense,
                    EventsMatches.AWAY_LINEUP_MIDFIELD to match.strAwayLineupMidfield,
                    EventsMatches.AWAY_LINEUP_FORWARD to match.strAwayLineupForward,
                    EventsMatches.AWAY_LINEUP_SUBSTITUTES to match.strAwayLineupSubstitutes
                )
            }
        }
        catch (e: SQLiteConstraintException){
            context.toast(e.localizedMessage).show()
        }
    }

    fun removeFavorite(context: Context,match: EventsMatches){
        try {
            context.database.use {
                delete(EventsMatches.TABLE_FAVORITES, "(ID_EVENT = {id})",
                    "id" to match.idEvent.toString())
            }
        }catch (e: SQLiteConstraintException){
            context.toast(e.localizedMessage).show()
        }
    }

    fun isFavorite(context: Context,match: EventsMatches): Boolean{
        var boolFavorite = false
        try{
            context.database.use {
                val favorite = select(EventsMatches.TABLE_FAVORITES).whereArgs("(ID_EVENT = {id})",
                    "id" to match.idEvent.toString())
                    .parseList(classParser<EventsMatches>())

            boolFavorite = !favorite.isEmpty()
            }
        }catch (e: SQLiteConstraintException){
            context.toast(e.localizedMessage).show()
        }
        return boolFavorite
    }
}