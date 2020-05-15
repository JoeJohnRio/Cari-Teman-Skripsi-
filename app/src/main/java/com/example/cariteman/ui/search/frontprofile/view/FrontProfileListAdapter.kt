package com.example.cariteman.ui.search.frontprofile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.FrontProfileResponse
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.FrontProfilePresenter
import com.example.cariteman.ui.search.filtersearch.view.FrontProfileMVPView

class FrontProfileListAdapter(var presenter: FrontProfilePresenter<FrontProfileMVPView>) :
    ListAdapter<FrontProfileResponse, RecyclerView.ViewHolder>(FrontProfileDiffCallBack()) {
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
        FrontProfile.PKL -> FrontProfilePklViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        FrontProfile.LOMBA -> FrontProfileLombaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        FrontProfile.TEMPATPKL -> FrontProfileTempatPklViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people_and_place,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FrontProfilePklViewHolder) {
            val response = getItem(position) as FrontProfileResponse

            holder.bind(response, presenter)
        }else if (holder is FrontProfileLombaViewHolder) {
            val response = getItem(position) as FrontProfileResponse

            holder.bind(response, presenter)
        }else if (holder is FrontProfileTempatPklViewHolder) {
            val response = getItem(position) as FrontProfileResponse

            holder.bind(response, presenter)
        }
    }
}

object FrontProfile {
    const val PKL = 0
    const val LOMBA = 1
    const val TEMPATPKL = 2
    const val KOSONG = 3
}