package com.example.irfan.footballmatchschedule.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.irfan.footballmatchschedule.Model.Favorite
import com.example.irfan.footballmatchschedule.Model.Team
import org.jetbrains.anko.db.*

class FavoritesDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){

    companion object {
        private var instance: FavoritesDatabaseOpenHelper? = null

        @Synchronized
        fun getInstace(ctx: Context): FavoritesDatabaseOpenHelper{
            if (instance == null){
                instance = FavoritesDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as FavoritesDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITES, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENT to TEXT + UNIQUE,
                Favorite.DATE to TEXT,
                Favorite.TIME to TEXT,

                Favorite.HOME_ID to TEXT,
                Favorite.HOME_TEAM to TEXT,
                Favorite.HOME_SCORE to TEXT,
//                Favorite.HOME_FORMATION to TEXT,
                Favorite.HOME_GOAL_DETAILS to TEXT,
                Favorite.HOME_SHOTS to TEXT,
                Favorite.HOME_LINEUP_GOALKEEPER to TEXT,
                Favorite.HOME_LINEUP_DEFENSE to TEXT,
                Favorite.HOME_LINEUP_MIDFIELD to TEXT,
                Favorite.HOME_LINEUP_FORWARD to TEXT,
                Favorite.HOME_LINEUP_SUBSTITUTES to TEXT,

                Favorite.AWAY_ID to TEXT,
                Favorite.AWAY_TEAM to TEXT,
                Favorite.AWAY_SCORE to TEXT,
//                Favorite.AWAY_FORMATION to TEXT,
                Favorite.AWAY_GOAL_DETAILS to TEXT,
                Favorite.AWAY_SHOTS to TEXT,
                Favorite.AWAY_LINEUP_GOALKEEPER to TEXT,
                Favorite.AWAY_LINEUP_DEFENSE to TEXT,
                Favorite.AWAY_LINEUP_MIDFIELD to TEXT,
                Favorite.AWAY_LINEUP_FORWARD to TEXT,
                Favorite.AWAY_LINEUP_SUBSTITUTES to TEXT,
                Favorite.STR_SPORT to TEXT)

        db.createTable(Team.TABLE_TEAM_FAVORITE, true,
                Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Team.TEAM_ID to TEXT + UNIQUE,
                Team.TEAM_NAME to TEXT,
                Team.TEAM_BADGE to TEXT,
                Team.TEAM_YEAR to TEXT,
                Team.TEAM_STADIUM to TEXT,
                Team.TEAM_DESC to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITES, true)
        db.dropTable(Team.TABLE_TEAM_FAVORITE, true)
    }
}

val Context.database: FavoritesDatabaseOpenHelper
    get() = FavoritesDatabaseOpenHelper.getInstace(applicationContext)