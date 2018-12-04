package com.example.irfan.footballmatchschedule.UI.SearchMatchUI

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
import com.example.irfan.footballmatchschedule.Adapter.MatchAdapter
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.R.string.match
import com.example.irfan.footballmatchschedule.UI.MatchDetailUI.MatchDetailActivity
import com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI.TeamDetailOverfiewFragment
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match_search.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast

class SearchMatchFragment: Fragment(), SearchMatchView{

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var name: String

    private var events: MutableList<EventsMatches> = mutableListOf()

    companion object {

        fun newInstance(name: String): SearchMatchFragment {
            val fragment = SearchMatchFragment()
            val args = Bundle()
            args.putString("name", name)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_match_search, container, false)
        recyclerView = viewRoot.find(R.id.recyclerViewMatchSearch)
        progressBar = viewRoot.find(R.id.progressBarMatchSearch)

        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        name = arguments?.getString("name")!!
        Log.e("datanya", name)

        presenter = SearchMatchPresenter(this, ApiRepository(), Gson())
        presenter.getAll(name)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = MatchAdapter(events){
            toast("${it.idEvent}")
            requireContext().startActivity<MatchDetailActivity>(MatchDetailActivity.INTENT to it)
        }
        recyclerView.adapter = adapter
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showAllMatch(match: List<EventsMatches>) {
        events.clear()
        var parse = match.filter { it.strSport!!.toLowerCase().contains("soccer") }
        events.addAll(parse)
        adapter.notifyDataSetChanged()
//        events.filter { it.strSport!!.toLowerCase().contains("soccer") }
        Log.e("datanya", match.toString())
    }
}