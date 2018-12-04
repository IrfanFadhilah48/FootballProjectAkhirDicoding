package com.example.irfan.footballmatchschedule.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(
    val id: Long?,
    val idEvent : String?,
    val dateEvent : String?,
    val strTime: String?,
    val idHomeTeam : String?,
    val strHomeTeam : String?,
    val intHomeScore : String?,
    val strHomeGoalDetails : String?,
    val intHomeShots : String?,
    val strHomeLineupGoalkeeper : String?,
    val strHomeLineupDefense : String?,
    val strHomeLineupMidfield : String?,
    val strHomeLineupForward : String?,
    val strHomeLineupSubstitutes : String?,
    val idAwayTeam : String?,
    val strAwayTeam : String?,
    val intAwayScore : String?,
    val strAwayGoalDetails : String?,
    val intAwayShots : String?,
    val strAwayLineupGoalkeeper : String?,
    val strAwayLineupDefense : String?,
    val strAwayLineupMidfield : String?,
    val strAwayLineupForward : String?,
    val strAwayLineupSubstitutes : String?,
    val strSport : String?
): Parcelable {
    companion object {
        const val TABLE_FAVORITES = "TABLE_FAVORITES"
        const val ID = "ID_"
        const val ID_EVENT = "ID_EVENT"
        const val DATE = "DATE"
        const val TIME = "TIME"

        const val HOME_ID = "HOME_ID"
        const val HOME_TEAM = "HOME_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val HOME_SHOTS = "HOME_SHOTS"
        const val HOME_LINEUP_GOALKEEPER = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELD = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_FORWARD = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTES = "HOME_LINEUP_SUBSTITUTES"

        const val AWAY_ID = "AWAY_ID"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETAILS"
        const val AWAY_SHOTS = "AWAY_SHOTS"
        const val AWAY_LINEUP_GOALKEEPER = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELD = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_FORWARD = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTES = "AWAY_LINEUP_SUBSTITUTES"
        const val STR_SPORT = "STR_SPORT"
    }
}