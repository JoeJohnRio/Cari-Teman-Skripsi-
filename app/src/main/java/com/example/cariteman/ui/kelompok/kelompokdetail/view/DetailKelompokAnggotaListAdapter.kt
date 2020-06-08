package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.AnggotaKelompok
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.dashboard.barudilihat.view.*

class DetailKelompokAnggotaListAdapter(var view: KelompokDetailFragment, var isWithRemove: Boolean) :
    ListAdapter<AnggotaKelompok, RecyclerView.ViewHolder>(DetailAnggotaKelompokDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        if (isWithRemove) {
            return Kelompok.WITHREMOVE
        } else {
            return Kelompok.WITHOUTREMOVE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        Kelompok.WITHOUTREMOVE -> DetailKelompokAnggotaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_anggota_kelompok,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        Kelompok.WITHREMOVE-> DetailKelompokAnggotaWithRemoveViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_anggota_kelompok_with_remove,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DetailKelompokAnggotaViewHolder) {
            val response = getItem(position) as AnggotaKelompok

            holder.bind(response, view)
        }else if (holder is DetailKelompokAnggotaWithRemoveViewHolder) {
            val response = getItem(position) as AnggotaKelompok

            holder.bind(response, view)
        }
    }

object Kelompok {
    const val WITHOUTREMOVE = 0
    const val WITHREMOVE= 1
}
}