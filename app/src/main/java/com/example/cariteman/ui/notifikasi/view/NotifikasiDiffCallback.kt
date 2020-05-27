package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class NotifikasiDiffCallback : DiffUtil.ItemCallback<NotifikasiResponse>() {
    override fun areItemsTheSame(oldItem: NotifikasiResponse, newItem: NotifikasiResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NotifikasiResponse, newItem: NotifikasiResponse): Boolean {
        return oldItem.id == newItem.id
    }
}