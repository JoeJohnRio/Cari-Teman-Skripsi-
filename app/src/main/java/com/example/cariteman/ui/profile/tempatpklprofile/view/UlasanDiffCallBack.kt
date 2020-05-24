package com.example.cariteman.ui.profile.tempatpklprofile.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.*

class UlasanDiffCallBack : DiffUtil.ItemCallback<UlasanTempatPklProfile>() {
    override fun areItemsTheSame(oldItem: UlasanTempatPklProfile, newItem: UlasanTempatPklProfile): Boolean {
        return oldItem.gambarPengirim == newItem.gambarPengirim
    }

    override fun areContentsTheSame(oldItem: UlasanTempatPklProfile, newItem: UlasanTempatPklProfile): Boolean {
        return oldItem.gambarPengirim == newItem.gambarPengirim
    }
}