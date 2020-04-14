package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView
import com.example.cariteman.ui.dashboard.view.FavoriteDiffCallback

class FavoriteListAdapter(presenter: DashboardPresenter<DashboardMVPView>) :
    ListAdapter<RelationTempatPklFavorite, RecyclerView.ViewHolder>(FavoriteDiffCallback()) {
    override fun getItemViewType(position: Int): Int {
        if (getItem(position).mahasiswaTwoPkl != null) {
            return 0
        } else if (getItem(position).mahasiswaTwoLomba != null) {
            return 1
        } else if (getItem(position).tempatPkl != null) {
            return 2
        } else {
            return 3
        }
    }

    var presenterAdapter = presenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        FavoriteType.PKL -> BaruDilihatPklViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        FavoriteType.LOMBA -> BaruDilihatLombaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        FavoriteType.TEMPATPKL -> BaruDilihatTempatPklViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BaruDilihatPklViewHolder) {
            val response = getItem(position) as RelationTempatPklFavorite

            holder.bindFavorite(response, presenterAdapter)
        }else if (holder is BaruDilihatLombaViewHolder) {
            val response = getItem(position) as RelationTempatPklFavorite

            holder.bindFavorite(response, presenterAdapter)
        }else if (holder is BaruDilihatTempatPklViewHolder) {
            val response = getItem(position) as RelationTempatPklFavorite

            holder.bindFavorite(response, presenterAdapter)
        }
    }
}

object FavoriteType {
    const val PKL = 0
    const val LOMBA = 1
    const val TEMPATPKL = 2
    const val KOSONG = 3
}