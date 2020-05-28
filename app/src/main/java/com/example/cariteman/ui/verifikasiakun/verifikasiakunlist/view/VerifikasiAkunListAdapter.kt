package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaNotVerifiedListResponse

class VerifikasiAkunListAdapter(var view: VerifikasiAkunListFragment) :
    ListAdapter<MahasiswaNotVerifiedListResponse, RecyclerView.ViewHolder>(
        VerifikasiAkunListDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VerifikasiAkunListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mahasiswa_not_verified,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val response = getItem(position) as MahasiswaNotVerifiedListResponse

        (holder as VerifikasiAkunListViewHolder).bind(response, view)
    }
}