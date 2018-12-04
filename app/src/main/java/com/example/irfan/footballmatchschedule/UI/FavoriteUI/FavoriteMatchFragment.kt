package com.example.irfan.footballmatchschedule.UI.FavoriteUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.irfan.footballmatchschedule.Adapter.FavoriteMatchAdapter
import com.example.irfan.footballmatchschedule.DB.database
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.MatchDetailUI.MatchDetailActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavoriteMatchFragment: Fragment(){

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var presenter: FavoriteMatchPresenter
//    private var favorites: MutableList<Favorite> = mutableListOf()
    private var favorites: MutableList<EventsMatches> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_favorite_match, container, false)
        swipeRefresh = viewRoot.find(R.id.swipeRefresh)
        listEvent = viewRoot.find(R.id.recylerviewFavorite)
        progressBar = viewRoot.find(R.id.progressbarFavorite)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listEvent.layoutManager = LinearLayoutManager(activity)
        adapter = FavoriteMatchAdapter(favorites){
            toast("anda memilih ${it.idEvent}")
            startActivity<MatchDetailActivity>(MatchDetailActivity.INTENT to it)
        }
        listEvent.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

//    private fun showFavorite() {
//        swipeRefresh.isRefreshing = false
//        presenter = FavoriteMatchPresenter(requireContext(), this)
//        adapter = FavoriteMatchAdapter(favorites){
//            toast("anda memilih ${it.idEvent}")
//            startActivity<MatchDetailActivity>(MatchDetailActivity.INTENT to it)
//        }
//        presenter.showFavorite()
//        listEvent.adapter = adapter
//    }

//    override fun showLoading() {
//        progressBar.visible()
//        listEvent.invisible()
//    }
//
//    override fun hideLoading() {
//        progressBar.invisible()
//        listEvent.visible()
//    }
//
//    override fun displayFavoriteMatch(data: List<EventsMatches>) {
//        favorites.clear()
//        favorites.addAll(data)
//        adapter.notifyDataSetChanged()
//        Log.e("hasil", favorites.toString())
//    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        progressBar.invisible()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            favorites.clear()
            val result = select(EventsMatches.TABLE_FAVORITES)
            val favorite = result.parseList(classParser<EventsMatches>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            Log.e("hasilakhir",favorites.toString())
        }
    }

}