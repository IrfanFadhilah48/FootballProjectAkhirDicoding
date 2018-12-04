package com.example.irfan.footballmatchschedule.UI.TeamDetailPlayerUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Adapter.PlayerAdapter
import com.example.irfan.footballmatchschedule.Model.TeamPlayer
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.DetailPlayerUI.DetailPlayerActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_detail_player.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast


class TeamDetailPlayerFragment: Fragment(), TeamDetailPlayerView{

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PlayerAdapter
    private lateinit var presenter: TeamDetailPlayerPresenter
    private var player: MutableList<TeamPlayer> = mutableListOf()

    private lateinit var id: String

    companion object {
        fun newInstance(id: String): TeamDetailPlayerFragment{
            val fragment = TeamDetailPlayerFragment()
            val args = Bundle()
            args.putString("id", id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_team_detail_player, container, false)
        recyclerView = viewRoot.find(R.id.recyclerViewPlayerTeamDetail)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        id = arguments?.getString("id")!!

        presenter = TeamDetailPlayerPresenter(this, ApiRepository(), Gson())
        presenter.getPlayerTeam(id)
        setDataLayout()
    }

    private fun setDataLayout() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PlayerAdapter(player){
            toast("Anda Memilih ${it.strPlayer}")
            context?.startActivity<DetailPlayerActivity>("name" to it.strPlayer)
        }
        recyclerView.adapter = adapter
    }

    override fun showLoading() {
        progressBarTeamDetailPlayer.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBarTeamDetailPlayer.invisible()
        recyclerView.visible()
    }

    override fun showPlayerTeam(data: List<TeamPlayer>) {
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()
    }

}