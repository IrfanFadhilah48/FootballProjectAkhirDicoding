package com.example.irfan.footballmatchschedule.UI.TeamDetailUI

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.irfan.footballmatchschedule.Adapter.ViewpagerAdapter
import com.example.irfan.footballmatchschedule.DB.database
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.R.id.add_to_favorite
import com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI.TeamDetailOverfiewFragment
import com.example.irfan.footballmatchschedule.UI.TeamDetailPlayerUI.TeamDetailPlayerFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class TeamDetailActivity: AppCompatActivity(){

    private lateinit var tabMatch: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var data: Team

    private var menuFavorite: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    companion object {
        val INTENTDETAIl = "INTENT_DATA_TEAM"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tabMatch = find(R.id.tab_team)
        viewPager = find(R.id.viewpager_team)

        id = intent.getStringExtra("id")
        data = intent.getParcelableExtra(INTENTDETAIl)


        setviewPager(viewPager)
        tabMatch.setupWithViewPager(viewPager)

        favoriteState()
    }

    private fun setviewPager(viewPager: ViewPager) {
        val adapter = ViewpagerAdapter(supportFragmentManager)
        adapter.addFragment(TeamDetailOverfiewFragment.newInstance(id), "OVERVIEW")
        adapter.addFragment(TeamDetailPlayerFragment.newInstance(id), "PLAYERS")
        viewPager.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuFavorite = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            add_to_favorite ->{
//                if(isFavorite){
//                    removeFavorite()
//                    toast("Remove from Favorite")
//                }else{
//                    addFavorite()
//                    toast("Added to Favorite")
//                }
                if (isFavorite){
                    removeFavorite()
//                    toast("Added to Favorite")
                } else{
                    addFavorite()
//                    toast("Remove from Favorite")
                }
                isFavorite = !isFavorite
                setFavorite()
                Log.e("bool", isFavorite.toString())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun favoriteState() {
        database.use {
            val result = select(Team.TABLE_TEAM_FAVORITE).whereArgs("(ID_TEAM = {id})",
                "id" to data.idTeam.toString())
            val favorite = result.parseList(classParser<Team>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addFavorite(){
        try {
            database.use {
                insert(
                    Team.TABLE_TEAM_FAVORITE,
                    Team.TEAM_ID to data.idTeam,
                    Team.TEAM_NAME to data.strTeam,
                    Team.TEAM_BADGE to data.strTeamBadge,
                    Team.TEAM_YEAR to data.intFormedYear,
                    Team.TEAM_STADIUM to data.strStadium,
                    Team.TEAM_DESC to data.strDescriptionEN
                )
            }
            toast("Added to Favorite")
        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    private fun removeFavorite(){
        try {
            database.use {
                delete(Team.TABLE_TEAM_FAVORITE, "(ID_TEAM = {id})",
                    "id" to data.idTeam.toString())
            }
            toast("Remove from Favorite")
        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite){
//            menuFavorite?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
            menuFavorite?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            menuFavorite?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }



}