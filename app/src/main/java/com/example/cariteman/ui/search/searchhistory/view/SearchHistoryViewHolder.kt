package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.SearchHistoryPresenter
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryFragment
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryMVPView
import kotlinx.android.extensions.LayoutContainer

class SearchHistoryViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var tvHistoryName: TextView? = null
    private var tvHistoryType: TextView? = null

    init {
        tvHistoryName = itemView.findViewById(R.id.tv_history_name)
        tvHistoryType = itemView.findViewById(R.id.tv_history_type)
    }

    fun bind(response: SearchHistory, presenter: SearchHistoryPresenter<SearchHistoryMVPView>, view: SearchHistoryFragment) {
        tvHistoryName?.setText(response.name)
        if (response.searchType == 0){
            tvHistoryType?.setText("PKL")
        }else if (response.searchType == 1){
            tvHistoryType?.setText("Lomba")
        }else if (response.searchType == 2){
            tvHistoryType?.setText("Tempat PKL")
        }

        itemView.setOnClickListener {
            view.searchWithSearchHistory(response)
        }

    }

}