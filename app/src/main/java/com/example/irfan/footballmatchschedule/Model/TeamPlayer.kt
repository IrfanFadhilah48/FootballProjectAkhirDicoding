package com.example.irfan.footballmatchschedule.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class TeamPlayer(
    val idPlayer: String,
    val strDescriptionEN: String,
    val strPlayer: String,
    val strPosition: String,
    val strThumb: String,
    val strHeight: String,
    val strWeight: String
)