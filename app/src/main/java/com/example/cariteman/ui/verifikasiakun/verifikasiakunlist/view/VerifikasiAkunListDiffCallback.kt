package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class VerifikasiAkunListDiffCallback : DiffUtil.ItemCallback<MahasiswaNotVerifiedListResponse>() {
    override fun areItemsTheSame(oldItem: MahasiswaNotVerifiedListResponse, newItem: MahasiswaNotVerifiedListResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MahasiswaNotVerifiedListResponse, newItem: MahasiswaNotVerifiedListResponse): Boolean {
        return oldItem.id == newItem.id
    }
}