package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class DaftarTemanHanyaNamaDiffCallback : DiffUtil.ItemCallback<DaftarTemanHanyaNama>() {
    override fun areItemsTheSame(oldItem: DaftarTemanHanyaNama, newItem: DaftarTemanHanyaNama): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DaftarTemanHanyaNama, newItem: DaftarTemanHanyaNama): Boolean {
        return oldItem.id == newItem.id
    }
}