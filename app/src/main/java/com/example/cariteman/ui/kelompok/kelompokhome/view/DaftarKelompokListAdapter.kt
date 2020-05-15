package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.ui.dashboard.barudilihat.view.*
import com.example.cariteman.ui.profile.view.ProfileActivity

class DaftarKelompokListAdapter(
    var type: Int,
    var kelompokHomeFragment: KelompokHomeFragment? = null,
    var kelompokHomeProfileActivity: ProfileActivity? = null
) :
    ListAdapter<RelationKelompok, RecyclerView.ViewHolder>(RelationKelompokDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        if (type == DaftarKelompokType.KELOMPOKHOMEFRAGMENT) {
            return 0
        } else if (type == DaftarKelompokType.KELOMPOKPROFILEACTIVITY) {
            return 1
        } else {
            return 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        DaftarKelompokType.KELOMPOKHOMEFRAGMENT -> DaftarKelompokViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_daftar_kelompok,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        DaftarKelompokType.KELOMPOKPROFILEACTIVITY -> DaftarKelompokProfileActivityViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_daftar_kelompok,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DaftarKelompokViewHolder) {
            val response = getItem(position) as RelationKelompok

            holder.bind(response, kelompokHomeFragment ?: KelompokHomeFragment())
        } else if (holder is DaftarKelompokProfileActivityViewHolder) {
            val response = getItem(position) as RelationKelompok

            holder.bind(response, kelompokHomeProfileActivity ?: ProfileActivity())
        }
    }
}

object DaftarKelompokType {
    const val KELOMPOKHOMEFRAGMENT = 0
    const val KELOMPOKPROFILEACTIVITY = 1
}