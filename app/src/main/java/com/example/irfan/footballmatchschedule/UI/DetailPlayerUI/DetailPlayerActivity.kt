package com.example.irfan.footballmatchschedule.UI.DetailPlayerUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Model.TeamPlayer
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class DetailPlayerActivity: AppCompatActivity(), DetailPlayerView{

    private lateinit var name: String
    private lateinit var presenter: DetailPlayerPresenter
    private var player: MutableList<TeamPlayer> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val intent = intent
        name = intent.getStringExtra("name")

        presenter = DetailPlayerPresenter(this, ApiRepository(), Gson())
        presenter.getPlayerDetail(name)
    }

    override fun showLoading() {
        progressBarPlayerDetail.visible()
    }

    override fun hideLoading() {
        progressBarPlayerDetail.invisible()
    }

    override fun showPlayerDetail(data: List<TeamPlayer>) {
        player.clear()
        player.addAll(data)

        supportActionBar?.title = data[0].strPlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(data[0].strThumb).into(imageViewPlayerDetail)

        textViewHeightPlayerDetail.text = data[0].strHeight
        textViewWeightPlayerDetail.text = data[0].strWeight
        textViewPositionPlayerDetail.text = data[0].strPosition
        textViewDescriptionPlayerDetail.text = data[0].strDescriptionEN
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}