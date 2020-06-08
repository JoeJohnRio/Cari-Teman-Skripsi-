package com.example.cariteman.ui.dashboard.rekomendasi.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.RekomendasiResponse

class ListRekomendasiDiffCallBack : DiffUtil.ItemCallback<RekomendasiResponse>() {
    override fun areItemsTheSame(oldItem: RekomendasiResponse, newItem: RekomendasiResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RekomendasiResponse, newItem: RekomendasiResponse): Boolean {
        return oldItem.id == newItem.id
    }
}