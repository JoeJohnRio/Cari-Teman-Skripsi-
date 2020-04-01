package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse

class BaruDilihatListAdapter :
    ListAdapter<MahasiswaHistoryDashboardResponse, RecyclerView.ViewHolder>(BaruDilihatDiffCallBack()) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        BaruDilihatType.PKL -> BaruDilihatPklViewHolder(
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
            val statusPost = getItem(position) as MahasiswaHistoryDashboardResponse

            holder.bind(statusPost)
        }
    }
}

object BaruDilihatType {
    const val PKL = 0
    const val LOMBA = 1
    const val TEMPATPKL = 2
    const val KOSONG = 3
}