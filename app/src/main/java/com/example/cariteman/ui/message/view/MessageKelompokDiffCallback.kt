package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class MessageKelompokDiffCallback : DiffUtil.ItemCallback<MessageKelompok>() {
    override fun areItemsTheSame(oldItem: MessageKelompok, newItem: MessageKelompok): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageKelompok, newItem: MessageKelompok): Boolean {
        return oldItem.id == newItem.id
    }
}