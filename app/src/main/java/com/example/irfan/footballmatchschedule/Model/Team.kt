package com.example.irfan.footballmatchschedule.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
        val id: Long?,
        val idTeam: String?,
        val strTeam: String?,
        val strTeamBadge: String?,
        val intFormedYear: String?,
        val strStadium: String?,
        val strDescriptionEN: String?
):Parcelable{
    companion object {
        const val TABLE_TEAM_FAVORITE = "TABLE_TEAM_FAVORITE"
        const val ID = "ID_"
        const val TEAM_ID = "ID_TEAM"
        const val TEAM_NAME = "NAME_TEAM"
        const val TEAM_BADGE = "BADGE_TEAM"
        const val TEAM_YEAR = "YEAR_TEAM"
        const val TEAM_STADIUM = "TEAM_STADIUM"
        const val TEAM_DESC = "TEAM_DESCRIPTION"
    }
}