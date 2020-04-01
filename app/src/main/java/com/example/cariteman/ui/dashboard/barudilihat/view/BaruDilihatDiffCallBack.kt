package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse

class BaruDilihatDiffCallBack : DiffUtil.ItemCallback<MahasiswaHistoryDashboardResponse>() {
    override fun areItemsTheSame(oldItem: MahasiswaHistoryDashboardResponse, newItem: MahasiswaHistoryDashboardResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MahasiswaHistoryDashboardResponse, newItem: MahasiswaHistoryDashboardResponse): Boolean {
        return oldItem.id == newItem.id
    }
}