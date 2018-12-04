package com.example.irfan.footballmatchschedule.UI.MatchDetailUI

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.DB.database
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.Favorite
import com.example.irfan.footballmatchschedule.Model.MatchDetail
import com.example.irfan.footballmatchschedule.Model.TeamItems
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.Utils.DateTimeConverter
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.example.irfan.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.irfan.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.irfan.footballmatchschedule.R.id.add_to_favorite
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MatchDetailActivity: AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var data: EventsMatches
    private lateinit var match: Favorite

    private var matches: MutableList<MatchDetail> = mutableListOf()
//    private var favorite: MutableList<Favorite> = mutableListOf()

    private var menuFavorite: Menu? = null
    private var isFavorite: Boolean = false

    companion object {
        const val INTENT = "INTENT_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        data = intent.getParcelableExtra(INTENT)

        progressBar = find(R.id.progressBarDetail)

        presenter = MatchDetailPresenter(this, ApiRepository(), Gson())
        isFavorite = presenter.isFavorite(this,data)
        presenter.getMatchDetail(data.idEvent.toString())


        presenter.getTeamDetail(data.idHomeTeam, data.idAwayTeam)
//        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuFavorite = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            add_to_favorite ->{
                if(isFavorite) {
                    presenter.removeFavorite(this, data)
//                    removeFavorite()
                    toast("Remove from Favorite")
                } else {
                    presenter.addFavorite(this, data)
//                    addFavorite()
                    toast("Added to Favorite")
                }
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeFavorite() {
        try {
            database.use {
//                delete(Favorite.TABLE_FAVORITES, "(ID_EVENT = {id})",
//                    "id" to data.idEvent.toString())
                delete(EventsMatches.TABLE_FAVORITES, "(ID_EVENT = {id})",
                        "id" to data.idEvent.toString())
            }
        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(
                        EventsMatches.TABLE_FAVORITES,
                        EventsMatches.ID_EVENT to data.idEvent,
//                        EventsMatches.DATE to data.dateEvent,

                        EventsMatches.HOME_ID to data.idHomeTeam,
                        EventsMatches.HOME_TEAM to data.strHomeTeam,
                        EventsMatches.HOME_SCORE to data.intHomeScore,
                        EventsMatches.HOME_GOAL_DETAILS to data.strHomeGoalDetails,
                        EventsMatches.HOME_SHOTS to data.intHomeShots,
                        EventsMatches.HOME_LINEUP_GOALKEEPER to data.strHomeLineupGoalkeeper,
                        EventsMatches.HOME_LINEUP_DEFENSE to data.strHomeLineupDefense,
                        EventsMatches.HOME_LINEUP_MIDFIELD to data.strHomeLineupMidfield,
                        EventsMatches.HOME_LINEUP_FORWARD to data.strHomeLineupForward,
                        EventsMatches.HOME_LINEUP_SUBSTITUTES to data.strHomeLineupSubstitutes,

                        EventsMatches.AWAY_ID to data.idAwayTeam,
                        EventsMatches.AWAY_TEAM to data.strAwayTeam,
                        EventsMatches.AWAY_SCORE to data.intAwayScore,
                        EventsMatches.AWAY_GOAL_DETAILS to data.strAwayGoalDetails,
                        EventsMatches.AWAY_SHOTS to data.intAwayShots,
                        EventsMatches.AWAY_LINEUP_GOALKEEPER to data.strAwayLineupGoalkeeper,
                        EventsMatches.AWAY_LINEUP_DEFENSE to data.strAwayLineupDefense,
                        EventsMatches.AWAY_LINEUP_MIDFIELD to data.strAwayLineupMidfield,
                        EventsMatches.AWAY_LINEUP_FORWARD to data.strAwayLineupForward,
                        EventsMatches.AWAY_LINEUP_SUBSTITUTES to data.strAwayLineupSubstitutes
//                    Favorite.TABLE_FAVORITES,
//                    Favorite.ID_EVENT to data.idEvent,
//                    Favorite.DATE to data.dateEvent,
//
//                    Favorite.HOME_ID to data.idHomeTeam,
//                    Favorite.HOME_TEAM to data.strHomeTeam,
//                    Favorite.HOME_SCORE to data.intHomeScore,
//                    Favorite.HOME_GOAL_DETAILS to data.strHomeGoalDetails,
//                    Favorite.HOME_SHOTS to data.intHomeShots,
//                    Favorite.HOME_LINEUP_GOALKEEPER to data.strHomeLineupGoalkeeper,
//                    Favorite.HOME_LINEUP_DEFENSE to data.strHomeLineupDefense,
//                    Favorite.HOME_LINEUP_MIDFIELD to data.strHomeLineupMidfield,
//                    Favorite.HOME_LINEUP_FORWARD to data.strHomeLineupForward,
//                    Favorite.HOME_LINEUP_SUBSTITUTES to data.strHomeLineupSubstitutes,
//
//                    Favorite.AWAY_ID to data.idAwayTeam,
//                    Favorite.AWAY_TEAM to data.strAwayTeam,
//                    Favorite.AWAY_SCORE to data.intAwayScore,
//                    Favorite.AWAY_GOAL_DETAILS to data.strAwayGoalDetails,
//                    Favorite.AWAY_SHOTS to data.intAwayShots,
//                    Favorite.AWAY_LINEUP_GOALKEEPER to data.strAwayLineupGoalkeeper,
//                    Favorite.AWAY_LINEUP_DEFENSE to data.strAwayLineupDefense,
//                    Favorite.AWAY_LINEUP_MIDFIELD to data.strAwayLineupMidfield,
//                    Favorite.AWAY_LINEUP_FORWARD to data.strAwayLineupForward,
//                    Favorite.AWAY_LINEUP_SUBSTITUTES to data.strAwayLineupSubstitutes
                )
            }
        }
        catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(EventsMatches.TABLE_FAVORITES).whereArgs("(ID_EVENT = {id})",
                    "id" to data.idEvent.toString())
            val favorite = result.parseList(classParser<EventsMatches>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
    private fun setFavorite() {
        if (isFavorite){
            menuFavorite?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        }else{
            menuFavorite?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetails(dataHomeTeam: List<TeamItems>, dataAwayTeam: List<TeamItems>) {
        Picasso.get().load(dataHomeTeam[0].strTeamBadge).into(imageHomeTeamDetail)
        Picasso.get().load(dataAwayTeam[0].strTeamBadge).into(imageAwayTeamDetail)
    }

    override fun showMatchDetails(dataDetailMatch: List<MatchDetail>) {
        supportActionBar?.title = dataDetailMatch[0].strEvent
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.getTeamDetail(dataDetailMatch[0].idHomeTeam, dataDetailMatch[0].idAwayTeam)
        matches.clear()
        matches.addAll(dataDetailMatch)

        textViewLeague.text = dataDetailMatch[0].strLeague
        textViewDateMatchDetail.text = DateTimeConverter.longDate(dataDetailMatch[0].dateEvent + " " + dataDetailMatch[0].strTime)
        textViewTimeMatchDetail.text = DateTimeConverter.timeDate(dataDetailMatch[0].dateEvent + " " + dataDetailMatch[0].strTime + " WIB")

        textViewHomeTeamDetail.text = dataDetailMatch[0].strHomeTeam
        textViewAwayTeamDetail.text = dataDetailMatch[0].strAwayTeam

        textViewGoalHomeTeamDetail.text = dataDetailMatch[0].strHomeGoalDetails
        textViewGoalAwayTeamDetail.text = dataDetailMatch[0].strAwayGoalDetails

        if (dataDetailMatch[0].intHomeShots == null || dataDetailMatch[0].intAwayShots == null){
            textViewShotsHomeTeamDetail.text = "-"
            textViewShotsAwayTeamDetail.text = "-"
        }else{
            textViewShotsHomeTeamDetail.text = dataDetailMatch[0].intHomeShots
            textViewShotsAwayTeamDetail.text = dataDetailMatch[0].intAwayShots
        }

        textViewHomeScore.text = dataDetailMatch[0].intHomeScore
        textViewAwayScore.text = dataDetailMatch[0].intAwayScore

        textViewGKHomeTeam.text = dataDetailMatch[0].strHomeLineupGoalkeeper
        textViewGKAwayTeam.text = dataDetailMatch[0].strAwayLineupGoalkeeper

        textViewDefendersHomeTeam.text = dataDetailMatch[0].strHomeLineupDefense
        textViewDefendersAwayTeam.text = dataDetailMatch[0].strAwayLineupDefense

        textViewMidefieldsHomeTeam.text = dataDetailMatch[0].strHomeLineupMidfield
        textViewMidefieldsAwayTeam.text = dataDetailMatch[0].strAwayLineupMidfield

        textViewForwardsHomeTeam.text = dataDetailMatch[0].strHomeLineupForward
        textViewForwardsAwayTeam.text = dataDetailMatch[0].strAwayLineupForward

        textViewSubtitutionsHomeTeam.text = dataDetailMatch[0].strHomeLineupSubstitutes
        textViewSubtitutionsAwayTeam.text = dataDetailMatch[0].strAwayLineupSubstitutes

        textViewYellowCardHomeTeam.text = dataDetailMatch[0].strHomeYellowCards
        textViewYellowCardAwayTeam.text = dataDetailMatch[0].strAwayYellowCards

        if (dataDetailMatch[0].strHomeRedCards.isNullOrEmpty() ||  dataDetailMatch[0].strAwayRedCards.isNullOrEmpty()){
            textViewRedCardsHomeTeam.text = "-"
            textViewRedCardsAwayTeam.text = "-"
        }else{
            textViewRedCardsHomeTeam.text = dataDetailMatch[0].strHomeRedCards
            textViewRedCardsAwayTeam.text = dataDetailMatch[0].strAwayRedCards
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}