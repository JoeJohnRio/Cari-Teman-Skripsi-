package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.DaftarTemanHanyaNama
import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.ui.dashboard.barudilihat.view.*

class DaftarTemanHanyaNamaListAdapter(view: TambahKelompok1Fragment) :
    ListAdapter<DaftarTemanHanyaNama, RecyclerView.ViewHolder>(DaftarTemanHanyaNamaDiffCallback()) {
    var tambahKelompok1 = view

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DaftarTemanViewHolder{
            return DaftarTemanViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_tambah_pilih_teman,
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val response = getItem(position) as DaftarTemanHanyaNama
            holder as DaftarTemanViewHolder
            holder.bind(response, tambahKelompok1)
    }
}