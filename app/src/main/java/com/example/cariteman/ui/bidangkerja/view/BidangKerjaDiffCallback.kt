package com.example.cariteman.ui.bidangkerja.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.BidangKerja

class BidangKerjaDiffCallback : DiffUtil.ItemCallback<BidangKerja>() {
    override fun areItemsTheSame(oldItem: BidangKerja, newItem: BidangKerja): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BidangKerja, newItem: BidangKerja): Boolean {
        return oldItem.id == newItem.id
    }
}