package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.BidangKerja
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.bidangkerja.view.BidangKerjaDiffCallback
import com.example.cariteman.ui.dashboard.barudilihat.view.ProfileDiffCallBack
import com.example.cariteman.ui.dashboard.barudilihat.view.ProfilePengalaman
import com.example.cariteman.ui.dashboard.barudilihat.view.ProfilePengalamanLombaViewHolder
import com.example.cariteman.ui.dashboard.barudilihat.view.ProfilePengalamanOrganisasiViewHolder

class SearchHistoryAdapter(view: PengalamanHomeFragment) :
    ListAdapter<PengalamanLombaOrganisasiResponse, RecyclerView.ViewHolder>(ProfileDiffCallBack()) {
    var pengalamanActivity = view

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).namaOrganisasi != null) {
            return 0
        } else if (getItem(position).namaKompetisi!= null) {
            return 1
        } else {
            return 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ProfilePengalaman.Organisasi -> ProfilePengalamanOrganisasiViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pengalaman_profile_with_modify,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        ProfilePengalaman.LOMBA -> ProfilePengalamanLombaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pengalaman_profile_with_modify,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProfilePengalamanOrganisasiViewHolder) {
            val response = getItem(position) as PengalamanLombaOrganisasiResponse

            holder.bindPengalaman(response, pengalamanActivity)
        }else if (holder is ProfilePengalamanLombaViewHolder) {
            val response = getItem(position) as PengalamanLombaOrganisasiResponse

            holder.bindPengalaman(response, pengalamanActivity)
        }
    }
}

object SEARCH {
    const val PKL = 0
    const val LOMBA = 1
    const val TEMPATPKL = 2
}