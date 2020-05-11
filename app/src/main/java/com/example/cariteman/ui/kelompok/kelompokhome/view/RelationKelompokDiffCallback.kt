package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class RelationKelompokDiffCallback : DiffUtil.ItemCallback<RelationKelompok>() {
    override fun areItemsTheSame(oldItem: RelationKelompok, newItem: RelationKelompok): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RelationKelompok, newItem: RelationKelompok): Boolean {
        return oldItem.id == newItem.id
    }
}