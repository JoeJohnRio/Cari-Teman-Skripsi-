package com.example.cariteman.ui.dashboard.barudilihat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RekomendasiResponse
import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatPresenter
import com.example.cariteman.ui.dashboard.barudilihat.presenter.RekomendasiPresenter
import com.example.cariteman.ui.dashboard.rekomendasi.view.ListRekomendasiDiffCallBack

class ListRekomendasiListAdapter(var context: Context, var presenter: RekomendasiPresenter<RekomendasiMVPView>) :
    ListAdapter<RekomendasiResponse, RecyclerView.ViewHolder>(ListRekomendasiDiffCallBack()) {
    override fun getItemViewType(position: Int): Int {
        if (getItem(position).typeOfRecommendation == 1) {
            return RekomendasiType.PKL
        } else if (getItem(position).typeOfRecommendation == 2) {
            return RekomendasiType.LOMBA
        } else if (getItem(position).typeOfRecommendation == 3) {
            return RekomendasiType.TEMPATPKL
        } else {
            return 4
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        RekomendasiType.PKL -> RekomendasiPklViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        RekomendasiType.LOMBA -> RekomendasiLombaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        RekomendasiType.TEMPATPKL -> RekomendasiTempatPklViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RekomendasiPklViewHolder) {
            val response = getItem(position) as RekomendasiResponse

            holder.bind(context, response, presenter)
        }else if (holder is RekomendasiLombaViewHolder) {
            val response = getItem(position) as RekomendasiResponse

            holder.bind(context, response, presenter)
        }else if (holder is RekomendasiTempatPklViewHolder) {
            val response = getItem(position) as RekomendasiResponse

            holder.bind(context, response, presenter)
        }
    }
}

object RekomendasiType {
    const val PKL = 1
    const val LOMBA = 2
    const val TEMPATPKL = 3
    const val KOSONG = 4
}