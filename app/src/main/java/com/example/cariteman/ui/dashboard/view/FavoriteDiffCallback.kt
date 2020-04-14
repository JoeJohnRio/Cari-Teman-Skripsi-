package com.example.cariteman.ui.dashboard.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite

class FavoriteDiffCallback : DiffUtil.ItemCallback<RelationTempatPklFavorite>() {
    override fun areItemsTheSame(oldItem: RelationTempatPklFavorite, newItem: RelationTempatPklFavorite): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RelationTempatPklFavorite, newItem: RelationTempatPklFavorite): Boolean {
        return oldItem.id == newItem.id
    }
}