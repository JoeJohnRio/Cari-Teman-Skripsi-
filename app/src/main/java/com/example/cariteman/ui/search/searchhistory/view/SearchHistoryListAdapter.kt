package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.ui.dashboard.barudilihat.view.*
import com.example.cariteman.ui.dashboard.presenter.SearchPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.SearchHistoryPresenter
import com.example.cariteman.ui.pengalaman.view.SearchActivity
import com.example.cariteman.ui.pengalaman.view.SearchMVPView
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryFragment
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryMVPView

class SearchHistoryListAdapter(var presenter: SearchHistoryPresenter<SearchHistoryMVPView>, var view: SearchHistoryFragment) :
    ListAdapter<SearchHistory, RecyclerView.ViewHolder>(SearchHistoryDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchHistoryViewHolder {
        return SearchHistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search_history,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as SearchHistoryViewHolder
            val response = getItem(position) as SearchHistory

        holder.bind(response, presenter, view)
    }
}

object SEARCH {
    const val PKL = 0
    const val LOMBA = 1
    const val TEMPATPKL = 2
}