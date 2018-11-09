package com.example.irfan.footballmatchschedule.UI.NextMatchUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Adapter.MatchAdapter
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.MatchDetailUI.MatchDetailActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private var events: MutableList<EventsMatches> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_next_match, container, false)
        progressBar = view.find(R.id.progressbarNextMatch)
        recyclerView = view.find(R.id.recylerviewNextMatch)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayout()
    }

    private fun setLayout(){
        presenter = NextMatchPresenter(this, ApiRepository(), Gson())
        adapter = MatchAdapter(events){
            toast("anda memilih ${it.idEvent}")
//            startActivity<NextMatchDetailActivity>(NextMatchDetailActivity.INTENT_NEXT to it)
            startActivity<MatchDetailActivity>(MatchDetailActivity.INTENT to it)
//            startActivity<MatchDetailActivity>("id" to "${it.idEvent}", "idHome" to "${it.idHomeTeam}", "awayTeam" to "${it.idAwayTeam}")
        }
        presenter.getEventLast()
        recyclerView.adapter = adapter
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showEventList(data: List<EventsMatches>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}