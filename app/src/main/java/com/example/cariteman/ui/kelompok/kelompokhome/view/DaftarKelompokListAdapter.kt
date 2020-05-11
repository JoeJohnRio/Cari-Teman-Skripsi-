package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.ui.dashboard.barudilihat.view.*

class DaftarKelompokListAdapter(view: KelompokHomeFragment) :
    ListAdapter<RelationKelompok, RecyclerView.ViewHolder>(RelationKelompokDiffCallback()) {
    var kelompokActivity = view

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DaftarKelompokViewHolder{
            return DaftarKelompokViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_daftar_kelompok,
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val response = getItem(position) as RelationKelompok
            holder as DaftarKelompokViewHolder
            holder.bind(response, kelompokActivity)
    }
}