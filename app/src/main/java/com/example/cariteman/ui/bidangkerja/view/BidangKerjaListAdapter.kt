package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.BidangKerja
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.bidangkerja.view.BidangKerjaDiffCallback
import com.example.cariteman.ui.bidangkerja.view.BidangKerjaViewHolder

class BidangKerjaListAdapter(view: BidangKerjaFragment) :
    ListAdapter<BidangKerja, RecyclerView.ViewHolder>(BidangKerjaDiffCallback()) {
    var bidangKerjaFragment = view

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidangKerjaViewHolder  {
        return BidangKerjaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bidang_kerja,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val response = getItem(position) as BidangKerja
            holder as BidangKerjaViewHolder
            holder.bindBidangKerja(response, bidangKerjaFragment)
    }
}
