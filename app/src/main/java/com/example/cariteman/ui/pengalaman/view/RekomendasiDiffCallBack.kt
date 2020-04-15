package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class RekomendasiDiffCallBack : DiffUtil.ItemCallback<Rekomendasi>() {
    override fun areItemsTheSame(oldItem: Rekomendasi, newItem: Rekomendasi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Rekomendasi, newItem: Rekomendasi): Boolean {
        return oldItem.id == newItem.id
    }
}