package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class DetailAnggotaKelompokDiffCallback : DiffUtil.ItemCallback<AnggotaKelompok>() {
    override fun areItemsTheSame(oldItem: AnggotaKelompok, newItem: AnggotaKelompok): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AnggotaKelompok, newItem: AnggotaKelompok): Boolean {
        return oldItem.id == newItem.id
    }
}